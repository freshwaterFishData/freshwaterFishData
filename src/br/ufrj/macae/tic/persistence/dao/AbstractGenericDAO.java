package br.ufrj.macae.tic.persistence.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * 
 * DAO Generico para hibernate
 *
 * @param <T>  classe final 
 * @param <ID> identificador do objeto
 * 
 */
public abstract class AbstractGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {
	
	/** classe de dominio */
	protected Class<T> persistentClass;

	/** (non-Javadoc)
	 * @see GenericDAO#getPersistentClass()
	 */
	public Class<T> getPersistentClass() {
        return persistentClass;
    }
	
	/**
	 * Constroi GenericDAOHibernate baseado no tipo da classe final
	 */
	@SuppressWarnings("unchecked")
	public AbstractGenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }
     
}
