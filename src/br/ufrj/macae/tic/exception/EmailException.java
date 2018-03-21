package br.ufrj.macae.tic.exception;

public class EmailException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6886859735548715053L;

	public EmailException(String mensagem) { 
		super(mensagem);
	}
	
	public EmailException(String mensagem, Throwable ex) { 
		super(mensagem, ex);
	}
}
