/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.modelo.dao.SubprojectDAO;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.persistence.entity.Subproject;
import br.ufrj.macae.tic.util.Util;


@Named
@SessionScoped
public class projectMB implements Serializable {		

	private static final long serialVersionUID = 3735988024311627776L;

	@Inject
	private Subproject subproject;	

	@Inject
	private SubprojectDAO subprojectDAO;	


	@Inject
	private PersonDAO personDAO;

	private List<Subproject> subprojectList;

	private List<Person> personList;


	public Subproject getSubproject() {
		return subproject;
	}

	public void setSubproject(Subproject subproject) {
		this.subproject = subproject;
	}


	public List<Subproject> getSubprojectList() {
		return subprojectList;
	}

	public void setSubprojectList(List<Subproject> subprojectList) {
		this.subprojectList = subprojectList;
	}

	public List<Person> getPersonList() {
		return personList;
	}



	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}



	/*
	 * Inicia as variáveis a serem usadas na tela de projeto
	 */
	@PostConstruct	
	public void init() {


		subprojectList = subprojectDAO.pesquisa();
		personList =  personDAO.getPersonOrderbyName();		

	} 

	/*
	 * Salva os dados de projeto
	 */
	public void save() {

		try {

			if(subproject.getId() != 0) {
				subprojectDAO.atualiza(subproject);
			} else {
				//1 = PELD		   
				subproject.setProjectId(1);   

				subprojectDAO.insere(subproject);   
				subproject = new Subproject();

			}  

			subprojectList = subprojectDAO.pesquisa();
			Mensagem.adicionarMensagemSucesso("Operação efetuada com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}


	/*
	 * Abre a tela de projetos após a exclusão de um projeto
	 */
	public String openDel() {

		return "../project/project";

	}

	/*
	 * Edita os dados do projeto
	 */
	public void edit() {

		try {			 

			subprojectDAO.atualiza(subproject);
			subprojectList = subprojectDAO.pesquisa();

			Mensagem.adicionarMensagemSucesso("Projeto atualizado com sucesso!");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}

	/*
	 * Remove os dados do projeto
	 */
	public void delete() {

		try {			 

			Subproject sb = new Subproject();			
			sb.setId(subproject.getId());
			subprojectDAO.apaga(sb);

			subprojectList = subprojectDAO.pesquisa();

			Mensagem.adicionarMensagemSucesso("Projeto removido com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_EXCLUIR);

		}
	}

	/*
	 * Abre a tela de alterção dos dados do projeto
	 */
	public String openAlter(Subproject s) {

		subproject = s;
		return "insert";
	}

	/*
	 * Abre a tela de cadastro de projetos
	 */
	public String open() {

		subproject = new Subproject();
		return "insert";
	} 

	/*
	 * Volta a listagem de projetos
	 */
	public String back() {

		return "project";
	}

}
