package br.ufrj.macae.tic.exception;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1764797755284369545L;

	public LoginException(String mensagem) { 
		super(mensagem);
	}

	public LoginException(String mensagem, Throwable ex) { 
		super(mensagem, ex);
	}

}
