package br.ufrj.macae.tic.persistence.dao.factory;

import br.ufrj.macae.tic.persistence.dao.GenericDAO;

/**
 * Interface para fabricas de DAO
 *
 */
public interface DAOFactory extends DAOFactoryConstants {
	/**
	 * Retorna GenericDAO baseado na classe
	 * @param  clazz Classe que sera instanciada pela fabrica 
	 * @return GenericDAO baseado na classe
	 * @throws DAOFactoryException 
	 */
	GenericDAO<?, ?> get(Class<?> clazz) throws DAOFactoryException;
}
