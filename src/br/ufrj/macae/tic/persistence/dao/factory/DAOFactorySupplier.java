package br.ufrj.macae.tic.persistence.dao.factory;

/**
 * Factory method para retornar fabricas concretas.
 */
public abstract class DAOFactorySupplier {
	
	/**
	 * Factory method para retornar fabricas concretas. 
	 * @param factory fabrica de DAO que sera instanciada
	 * @return instancia da fabrica de DAO
	 * @throws DAOFactoryException 
	 */
	public static DAOFactory get(final DAOFactoryEnum factory) throws DAOFactoryException {
		DAOFactory daoFactory;
		
        try {
        	daoFactory = (DAOFactory)factory.getFactoryClass().newInstance();
			return daoFactory;
		
        } catch (InstantiationException e) {
			throw new DAOFactoryException(e.getMessage(), e);

		} catch (IllegalAccessException e) {
			throw new DAOFactoryException(e.getMessage(), e);
		
		}
    }
}

