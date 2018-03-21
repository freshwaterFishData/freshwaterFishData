package br.ufrj.macae.tic.persistence.dao.factory;


import br.ufrj.macae.tic.persistence.dao.GenericDAO;
import br.ufrj.macae.tic.persistence.dao.hibernate.DAOFactoryHibernate;
import br.ufrj.macae.tic.persistence.dao.hibernate.GenericDAOHibernate;

/**
 * Enum das fabricas de DAO
 * Cada item do enum corresponde a uma fabrica de DAO e qual superclasse/interface 
 * (DAO generico) o DAO devera estender/implementar do framework.
 *
 */
public enum DAOFactoryEnum {
	/**
	 * identificador de fabrica de DAO Hibernate
	 */
	HIBERNATE	(DAOFactoryHibernate.class,	GenericDAOHibernate.class),
	/**
	 * identificador de fabrica de DAO jdbc/oracle
	 */
	//JDBC_ORACLE	(DAOFactoryOracle.class,	GenericDAOOracle.class),
	/**
	 * identificador de fabrica de DAO n�o preso a tecnologia
	 */
	GENERIC	(DAOFactoryGeneric.class,	GenericDAO.class)
	;
	
	/**
	 * classe da fabrica de DAOs
	 */
	private Class<?> factoryClass;
	/**
	 * classe do DAO generico relativo a fabrica
	 */
	private Class<?> genericDAOClass;
	
	/**
	 * Construtor do enum   
	 * @param factoryClass classe da fabrica de DAOs
	 * @param genericDAOClass classe de DAO Generico que a fabrica retorna
	 */
	private DAOFactoryEnum(Class<?> factoryClass, Class<?> genericDAOClass){
		this.factoryClass = factoryClass;
		this.genericDAOClass = genericDAOClass;
	}
	
	/**
	 * Retorna a classe da fabrica de DAOs do item
	 * @return a classe da fabrica de DAOs do item
	 */
	public Class<?> getFactoryClass(){
		return factoryClass; 
	}

	/**
	 * Retorna DAO generico relativo a fabrica
	 * @return DAO generico relativo a fabrica
	 */
	public Class<?> getGenericDAOClass() {
		return genericDAOClass;
	}
	
}
