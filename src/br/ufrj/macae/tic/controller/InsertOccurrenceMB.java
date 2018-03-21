/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.DispositionDAO;
import br.ufrj.macae.tic.modelo.dao.EstableshimentDAO;
import br.ufrj.macae.tic.modelo.dao.OccurrenceDAO;
import br.ufrj.macae.tic.modelo.dao.OrganismQuantityTypeDAO;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.modelo.dao.SampleDAO;
import br.ufrj.macae.tic.modelo.dao.TaxonDAO;
import br.ufrj.macae.tic.persistence.entity.Disposition;
import br.ufrj.macae.tic.persistence.entity.Estableshiment;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.OrganismQuantityType;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.persistence.entity.Recorder;
import br.ufrj.macae.tic.persistence.entity.Taxon;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class InsertOccurrenceMB implements Serializable {	


	/**
	 * 
	 */
	private static final long serialVersionUID = 6094420815602636425L;

	@Inject
	private Occurrence occurrence;

	@Inject
	private Event event;

	@Inject
	private OccurrenceDAO occurrenceDAO;

	@Inject
	private TaxonDAO taxonDAO;

	@Inject
	private EstableshimentDAO estableshimentDAO;

	@Inject
	private OrganismQuantityTypeDAO organismQuantityTypeDAO;

	@Inject
	private DispositionDAO dispositionDAO;

	@Inject
	private PersonDAO personDAO;

	@Inject
	private SampleDAO sampleDAO;

	private List<Occurrence> occurrenceList;

	private List<Taxon> taxonList;
	private List<Estableshiment> estableshimentList;
	private List<OrganismQuantityType> organismQuantityList;

	private List<Disposition> dispositionList;
	private List<Person> personList2;

	private List<Person> personList;

	public Occurrence getOccurrence() {
		return occurrence;
	}


	public void setOccurrence(Occurrence occurrence) {
		this.occurrence = occurrence;
	}


	public List<Occurrence> getOccurrenceList() {
		return occurrenceList;
	}


	public void setOccurrenceList(List<Occurrence> occurrenceList) {
		this.occurrenceList = occurrenceList;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Taxon> getTaxonList() {
		return taxonList;
	}


	public void setTaxonList(List<Taxon> taxonList) {
		this.taxonList = taxonList;
	}


	public List<Estableshiment> getEstableshimentList() {
		return estableshimentList;
	}


	public void setEstableshimentList(List<Estableshiment> estableshimentList) {
		this.estableshimentList = estableshimentList;
	}


	public List<OrganismQuantityType> getOrganismQuantityList() {
		return organismQuantityList;
	}


	public void setOrganismQuantityList(List<OrganismQuantityType> organismQuantityList) {
		this.organismQuantityList = organismQuantityList;
	}


	public List<Disposition> getDispositionList() {
		return dispositionList;
	}


	public void setDispositionList(List<Disposition> dispositionList) {
		this.dispositionList = dispositionList;
	}


	public List<Person> getPersonList2() {
		return personList2;
	}


	public void setPersonList2(List<Person> personList2) {
		this.personList2 = personList2;
	}


	public List<Person> getPersonList() {
		return personList;
	}


	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}


	/*
	 * Inicia as variáveis a serem utilizadas na tela de inserção de ocorrência
	 */
	@PostConstruct
	public void init() {

		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");

		Long id = Long.parseLong(p);

		event = sampleDAO.recupera(id);


		taxonList = taxonDAO.pesquisa();			
		estableshimentList =  estableshimentDAO.pesquisa();			
		organismQuantityList =  organismQuantityTypeDAO.pesquisa();			
		dispositionList =  dispositionDAO.pesquisa();
		personList =  personDAO.getPersonOrderbyName();		

	}



	/*
	 * Salva os dados de ocorrência
	 */
	public void save() {

		try {


			String p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");

			Long id = Long.parseLong(p);

			occurrence.getEvent().setId(id);   
			occurrenceDAO.insere(occurrence);

			Mensagem.adicionarMensagemSucesso("Ocorrência cadastrada com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}



	/**
	 * abre a tela de inserção de dados de ocorrência
	 * @return
	 */
	public String open() {

		return "insert";

	}

	/*
	 * Abre a tela de organismo
	 */
	public void openOrganism() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listOrganism.jsf?id=" + occurrence.getId() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Limpa os dados do formulário de inserção de ocorrência
	 */
	public String clean() {

		Occurrence aux = new Occurrence();
		aux.setEvent(event);

		occurrence = new Occurrence();	
		occurrence.setEvent(aux.getEvent());
		occurrence.setIdentifier(new Person());
		occurrence.setOrganismQuantityTypeId(new OrganismQuantityType());
		occurrence.setRecorder(new Recorder());
		occurrence.setTaxon(new Taxon());

		return "insert";

	}

	/**
	 * Busca todos os taxos que são utilizados na tela de inserção de ocorrência
	 */
	public void populateTaxon() {

		taxonList = taxonDAO.pesquisa();	
	}

}
