package br.ufrj.macae.tic.persistence;

import br.ufrj.macae.tic.persistence.dao.DAOException;



/**
 * Excecao para restricoes de integridade
 * 
 */
public class ConstraintException extends DAOException {
	
	/** necessario para serializable */
	private static final long serialVersionUID = 1L;

	/**
	 * Constroi execao vazia com mensagem padr�o
	 */
	public ConstraintException() {
		super();
	}

	/**
	 * Constroi excecao baseada em mensagem
	 * 
	 * @param message
	 */
	public ConstraintException(String message) {
		super(message);
	}

	/**
	 * Controi mensagem baseada em causa
	 * 
	 * @param cause
	 */
	public ConstraintException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constroi excecao baseada em mensagem e causa
	 * 
	 * @param message
	 * @param cause
	 */
	public ConstraintException(String message, Throwable cause) {
		super(message, cause);
	}
	
}


