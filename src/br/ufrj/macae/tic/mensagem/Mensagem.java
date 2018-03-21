package br.ufrj.macae.tic.mensagem;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensagem {
	
	public static String MSG_ERRO_GENERICO = "Ocorreu um comportamento inesperado no sistema, por favor, tente mais tarde.";
	
	

	
public static void adicionarMensagemSucesso(String mensagem) {
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null);
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		contexto.addMessage(null, msg);
	}
	

public static void adicionarMensagemSucesso() {
	
	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação efetuada com sucesso.", null);
	
	FacesContext contexto = FacesContext.getCurrentInstance();
	
	contexto.addMessage(null, msg);
}

 	public static void adicionarMensagemErro(String mensagem) {
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem , null);
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		contexto.addMessage(null, msg);
        
	}

   public static void adicionarMensagemErro() {
	
	//Mensagem.adicionarMensagemErro("Ocorreu um erro inesperado, por favor, contacte nosso suporte.");
	    String mensagem = "Ocorreu um erro inesperado, por favor, contacte nosso suporte.";
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem , null);
		
		FacesContext contexto = FacesContext.getCurrentInstance();
		
		contexto.addMessage(null, msg);
        
	}
}
