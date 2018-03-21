package br.ufrj.macae.tic.persistence.dao;


/**
 * 
 * Excecao lancada pela camada de persistencia 
 * 
 */
public class DAOException extends Exception {
	/**
	 * Estende serializable 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * (non-Javadoc)
	 * @see java.lang.Exception#Exception(Throwable cause)
	 */  
	public DAOException() {
		super();
	}

	/**
	 * (non-Javadoc)
	 * @param message 
	 * @see java.lang.Exception#Exception(Throwable cause)
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Exception#Exception(Throwable cause)
	 */
	public DAOException(Throwable cause) {
		super(cause);
	}

	/**
	 * (non-Javadoc)
	 * @see java.lang.Exception#Exception(String message, Throwable cause)
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

}
