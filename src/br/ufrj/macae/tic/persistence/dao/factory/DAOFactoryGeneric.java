package br.ufrj.macae.tic.persistence.dao.factory;

import br.ufrj.macae.tic.persistence.dao.GenericDAO;

/**
 * Abstracao das rotinas de Fabricas de DAOs
 */
public class DAOFactoryGeneric implements DAOFactory {

	/**
	 * Retorna inst�ncia do DAO gen�rico
	 * @param DAOClass classe que implemente a interface GenericDAO
	 * @return DAO instanciado
	 * @throws DAOFactoryException
	 */
	public GenericDAO<?,?> get(Class<?> DAOClass) throws DAOFactoryException {
        try {
        	GenericDAO<?,?> dao = (GenericDAO<?,?>)DAOClass.newInstance();
            return dao;
            
        } catch (Exception e) {
        	Object[] args = {DAOClass.getSimpleName()};
            throw new DAOFactoryException(DAOFactoryMessages.INSTANTIATE_ERROR.toString(args), e);
        }
    }
}
