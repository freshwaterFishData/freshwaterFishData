package br.ufrj.macae.tic.persistence.dao.hibernate;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Classe utilitaria para configuracao/utilizacao do hibernate 
 *
 */
public class HibernateUtil implements Serializable{
	/** */
	private static final long serialVersionUID = -1316431739575902186L;
	
	/**
	 * SessionFactory do hibernate
	 */
    private static SessionFactory sessionFactory;
    
   
    /**
     * Retorna SessionFactory do Hibernate
     * @return SessionFactory do Hibernate
     * @throws IllegalStateException
     */
    public static SessionFactory getSessionFactory() throws IllegalStateException {
    	try {
    		// Cria a SessionFactory do Hibernate do arquivo hibernate.cfg.xml
    		sessionFactory = new AnnotationConfiguration().configure("/br/ufrj/macae/tic/modelo/hibernate.cfg.xml").buildSessionFactory();
    		return sessionFactory;
    	} catch (Throwable ex) {
    		throw new ExceptionInInitializerError(ex);
    	}
    }


    
    
    
}
