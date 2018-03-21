/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.util.Util;

/**
 * @author Equipe de desenvolvimento TIC/Macaé
 * 
 *         AbstractGenericDAOHibernate ela tem metodos de inclusão, alteração,
 *         exclusão e consulta. Consulta-la antes de criar métodos desse tipo
 *
 */

@Named
@RequestScoped
@ManagedBean
public class PersonMB {

	@Inject
	private PersonDAO personDAO;
	
	@Inject
	private Person person;
		

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	/*
	 * Loga no sistema
	 */
	public void logar() {

		try {

			person.setPassword(Util.criptografaSenha(person.getPassword()));
			//person = personDAO.autenticarUsuario(person);

			// guarde o usuário na sessão
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			session.setAttribute("person", person);

			
			FacesContext.getCurrentInstance().getExternalContext().redirect("../templates/template.jsf");
			

		} 
		//catch (LoginException e) {

			//FacesContext.getCurrentInstance().addMessage(null,
			//		new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), null));
		//}
		catch (Exception e) {
			e.printStackTrace();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Mensagem.MSG_ERRO_GENERICO, null));
		}

	}

	/*
	 * Faz logout no sistema
	 */
	public void logout() {

		try {

			// guarde o usuaário na sessão
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.invalidate();
						
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Mensagem.MSG_ERRO_GENERICO, null));
		}

	}
	
	
}
