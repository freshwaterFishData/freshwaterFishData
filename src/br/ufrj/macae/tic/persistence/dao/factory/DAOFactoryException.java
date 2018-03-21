package br.ufrj.macae.tic.persistence.dao.factory;

import br.ufrj.macae.tic.persistence.dao.DAOException;


/**
 * Excecao lancada pelas fabricas de DAO
 *
 */
public class DAOFactoryException extends DAOException {
	
	/** necessario para serializable */
	private static final long serialVersionUID = 2076160769766128027L;

	/**
	 * Constroi nova excecao com nulo como mensagem
	 */
	public DAOFactoryException() {
		super();
	}

	/**
	 * Constroi nova excecao com a mensagem especificada
	 * @param message mensagem para excecao
	 */
	public DAOFactoryException(String message) {
		super(message);
	}

	
	/**
	 * Constroi nova excecao com a causa especificada 
	 * @param cause causa da excecao
	 */
	public DAOFactoryException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constroi nova excecao com mensagem e causa espeficadas
	 * @param message mensagem da excecao
	 * @param cause causa da excecao
	 */
	public DAOFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
