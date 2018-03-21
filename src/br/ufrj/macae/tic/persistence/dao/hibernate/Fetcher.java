package br.ufrj.macae.tic.persistence.dao.hibernate;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import br.ufrj.macae.tic.persistence.AbstractEntity;

/**
 * Metodos para fetch automatico de retorno
 *
 */
public abstract class Fetcher {
	
	/**
	 * Inspeciona objeto forcando o fetch de atributos lazy
	 * @param lazyCandidate 
	 */
	public static void fetchAll(Object lazyCandidate) {
    	fetchAll(lazyCandidate, 0, -1);
	}

	/**
	 * Inspeciona objeto forcando o fetch de atributos lazy, at� uma profundidade limite
	 * @param lazyCandidate
	 * @param recursionLevel
	 * @param maxRecursionLevel limite de recursao, -1 equivale a infinito
	 */
	public static void fetchAll(Object lazyCandidate, int recursionLevel, int maxRecursionLevel) {
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(lazyCandidate.getClass());
			PropertyDescriptor[] beanProperties = beanInfo.getPropertyDescriptors();
			
			for (int i = 0; i < beanProperties.length; i++) {
				PropertyDescriptor property = beanProperties[i];
				Class<?> propertyType = property.getPropertyType();
				if (propertyType == null) continue;
				Method readMethod = property.getReadMethod();
				if(readMethod == null) continue;
				Class<?> returnType = readMethod.getReturnType();
				if(Collection.class.isAssignableFrom(returnType)){
					Collection<?> collection = (Collection<?>)readMethod.invoke(lazyCandidate);
					if(maxRecursionLevel == -1 || recursionLevel < maxRecursionLevel){
						for(Object element: collection){
							fetchAll(element, ++recursionLevel, maxRecursionLevel);
						}
					}
				} else {
					readMethod.invoke(lazyCandidate);
				}
			}
			
		} catch (Throwable e) {
			throw new HibernateException(e);
			
		}
	}

    /**
     * Inspeciona objeto forcando o fetch de atributos lazy, e converte para um objeto sem vinculo com hibernate
     * Limitacao: at� o momento opera somente sobre colecoes do tipo List, convertendo retornos para ArrayList
     * @param lazyCandidate
     * @param detached
     */
    @SuppressWarnings("unchecked")
	public static void fetchAndDetach(Object lazyCandidate, Object detached){
    	try {
			BeanInfo beanInfo = Introspector.getBeanInfo(detached.getClass());
			PropertyDescriptor[] beanProperties = beanInfo.getPropertyDescriptors();
			
			if(detached == null) throw new IllegalArgumentException("lazyCandidate");
			if(detached == null) throw new IllegalArgumentException("detached");
			
			for (int i = 0; i < beanProperties.length; i++) {
				PropertyDescriptor property = beanProperties[i];
				Class<?> propertyType = property.getPropertyType();
				if (propertyType == null) continue;
				Method readMethod = property.getReadMethod();
				if(readMethod == null) continue;
				Method writeMethod 	= property.getWriteMethod();
				if(writeMethod == null) continue;
				Class<?> returnType = readMethod.getReturnType();
				Object returnValue = readMethod.invoke(lazyCandidate);
				Object detachedValue = null;
				
				if(Collection.class.isAssignableFrom(returnType)){
					Collection<?> collection = (Collection<?>)returnValue;
					detachedValue = new ArrayList<Object>();
					for(Object lazyElement: collection){
						Object detachedElement = lazyElement.getClass().newInstance();
						fetchAndDetach(lazyElement, detachedElement);
						((List)detachedValue).add(detachedElement);
					}
				} else {
					if(returnValue != null && returnValue.getClass().isAssignableFrom(AbstractEntity.class)){
						detachedValue = returnValue.getClass().newInstance();
						fetchAndDetach(returnValue, detachedValue);
					} else {
						detachedValue = returnValue;
					}
				}
				writeMethod.invoke(detached, detachedValue);
			}
			
		} catch (Throwable e) {
			throw new HibernateException(e);
		}
    }
	
	
}
