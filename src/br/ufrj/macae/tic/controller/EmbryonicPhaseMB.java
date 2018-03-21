/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.EmbryonicPhaseDAO;
import br.ufrj.macae.tic.modelo.dao.OccurrenceDAO;
import br.ufrj.macae.tic.modelo.dao.OrganismDAO;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.persistence.entity.EmbryonicPhase;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.Organism;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class EmbryonicPhaseMB implements Serializable {		

	private static final long serialVersionUID = 3735988024311627776L;

	@Inject
	private EmbryonicPhase embryonicPhase;

	private Organism organism;	

	@Inject
	private OrganismDAO organismDAO;	

	@Inject
	private EmbryonicPhaseDAO embryonicPhaseDAO;

	private Occurrence occurrence;

	@Inject
	private OccurrenceDAO occurrenceDAO;	


	@Inject
	private PersonDAO personDAO;

	private List<EmbryonicPhase> embryonicPhaseList;

	private List<Person> personList;

	private List<Organism> organismList;

	public Organism getOrganism() {
		return organism;
	}

	public void setOrganism(Organism organism) {
		this.organism = organism;
	}

	public EmbryonicPhase getEmbryonicPhase() {
		return embryonicPhase;
	}

	public void setEmbryonicPhase(EmbryonicPhase embryonicPhase) {
		this.embryonicPhase = embryonicPhase;
	}

	public List<EmbryonicPhase> getEmbryonicPhaseList() {
		return embryonicPhaseList;
	}

	public void setEmbryonicPhaseList(List<EmbryonicPhase> embryonicPhaseList) {
		this.embryonicPhaseList = embryonicPhaseList;
	}

	public List<Person> getPersonList() {
		return personList;
	}



	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}


	public List<Organism> getOrganismList() {
		return organismList;
	}

	public void setOrganismList(List<Organism> organismList) {
		this.organismList = organismList;
	}

	/**
	 * inicia as váriveis a serem utilizadas na tela d eorganismo
	 */
	@PostConstruct	
	public void init() {


		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");

		Long id = Long.parseLong(p);

		organism = organismDAO.recupera(id);	 

		embryonicPhaseList= organism.getEmbryonicPhaseList();


		//personList =  personDAO.getPersonOrderbyName();		

	} 

	/*
	 * salva os dados de sobre um organismo
	 */
	public void save() {

		try {

			embryonicPhase.setOrganism(organism);   
			embryonicPhaseDAO.insere(embryonicPhase);

			loadOrganism();

			Mensagem.adicionarMensagemSucesso("Organismo cadastrado com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}


	public String openDel() {

		return "../ocurrence/listOccurrance";

	}

	
	/*
	 * atualiza os dados de sobre um organismo
	 */
	public void edit() {

		try {			 


			embryonicPhaseDAO.atualiza(embryonicPhase);

			loadOrganism();

			Mensagem.adicionarMensagemSucesso("Fase embrionária atualizada com sucesso!");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}

	/*
	 * remove os dados de sobre um organismo
	 */
	public void delete() {

		try {			 

			embryonicPhaseDAO.apaga(embryonicPhase);

			loadOrganism();

			Mensagem.adicionarMensagemSucesso("Fase embrionária removida com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_EXCLUIR);

		}
	}

	private void loadOrganism() {
		organism  = organismDAO.recupera(organism.getId());
		embryonicPhaseList = organism.getEmbryonicPhaseList();
	}

	public String back() {


		organismList = organism.getOccurrence().getOrganismList();

		return "listOrganism";
	}	


}
