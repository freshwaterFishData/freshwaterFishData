package br.ufrj.macae.tic.persistence.dao.factory;


/**
 * Abstracao das rotinas de Fabricas de DAOs
 */
public abstract class AbstractDAOFactory {

	/**
	 * Retorna DAO baseado na fabrica padr�o do framework e tipo informados
	 * @param tipo classe do DAO
	 * @return instancia do DAO
	 * @throws DAOFactoryException
	 */
	public static Object getDAO(Class<?> tipo)throws DAOFactoryException{
			return AbstractDAOFactory.getFrameworkDAO(DAOFactoryConstants.DEFAULT_FACTORY_TYPE, tipo);
	}

	/**
	 * Retorna DAO baseado na fabrica e tipo informados
	 * @param factoryType tipo da f�brica
	 * @param tipo classe do DAO
	 * @return instancia do DAO
	 * @throws DAOFactoryException
	 */
	public static Object getDAO(DAOFactoryEnum factoryType, Class<?> tipo)throws DAOFactoryException{
		return AbstractDAOFactory.getFrameworkDAO(factoryType, tipo);
	}
	
	/**
	 * Retorna DAO do framework
	 * @param daoFactoryType tipo de fabrica (hibernate, jdbc/oracle, etc)
	 * @param tipo classe do DAO a ser instanciada
	 * @return instancia do DAO
	 * @throws DAOFactoryException
	 */
	public static Object getFrameworkDAO(DAOFactoryEnum daoFactoryType,  Class<?> tipo)throws DAOFactoryException{
		Object daoInstance    = null;
		DAOFactory daoFactory = null;

		daoFactory  = DAOFactorySupplier.get(daoFactoryType);
		daoInstance = daoFactory.get(tipo) ;
		return daoInstance;
	}
	
	/**
	 * Retorna DAO do jkernel
	 * @param tipo classe do DAO a ser instanciada
	 * @return DAO do jkernel
	 * @throws DAOFactoryException
	 */
	public static Object getJkernelDAO(Class<?> tipo)throws DAOFactoryException{
		Object daoInstance = null;
		
		try {
			daoInstance = tipo.newInstance();
			return daoInstance;
			
		} catch (InstantiationException e) {
			throw new DAOFactoryException(e.getMessage(), e);
			
		} catch (IllegalAccessException e) {
			throw new DAOFactoryException(e.getMessage(), e);
		}
	}

}
