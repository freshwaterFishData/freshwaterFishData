package br.ufrj.macae.tic.persistence.dao.hibernate;

import org.hibernate.Session;

import br.ufrj.macae.tic.persistence.dao.factory.DAOFactory;
import br.ufrj.macae.tic.persistence.dao.factory.DAOFactoryException;
import br.ufrj.macae.tic.persistence.dao.factory.DAOFactoryMessages;

/**
 * Fabrica de DAOs Hibernate 
 *
 */
public class DAOFactoryHibernate implements DAOFactory{
	
	private String MONITORIADS = "monitoriaDS";
	
	/**
	 * retorna sessao atual do hibernate
	 * @param sessionFactoryName nome da SessionFactory do hibernate 
	 * @return sessao atual do hibernate
	 */
    protected Session getCurrentSession(String sessionFactoryName) {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    /**
     * Retorna instancia DAO baseado na classe passada como parametro
     * @param hibernateDAOClass classe do DAO
     * @return instancia do DAO
     * @throws DAOFactoryException 
     */
    @SuppressWarnings("unchecked")
	public GenericDAOHibernate get(Class hibernateDAOClass) throws DAOFactoryException {
        try {
            GenericDAOHibernate dao = (GenericDAOHibernate)hibernateDAOClass.newInstance();
            dao.setSession(getCurrentSession(MONITORIADS));
            
            return dao;
            
        } catch (Exception e) {
        	Object[] args = {hibernateDAOClass.getSimpleName()};
            throw new DAOFactoryException(DAOFactoryMessages.INSTANTIATE_ERROR.toString(args), e);
        }
    }
}
