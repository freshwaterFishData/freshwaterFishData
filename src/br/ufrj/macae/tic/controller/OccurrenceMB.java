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
import br.ufrj.macae.tic.persistence.entity.Taxon;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class OccurrenceMB implements Serializable {	


	/**
	 * 
	 */
	private static final long serialVersionUID = 6094420815602636425L;

	@Inject
	private Occurrence occurrence;

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


	private List<Person> personList;

	private List<Event> eventList;

	@Inject
	private Person identifier;

	@Inject
	private Person recorder;

	private boolean first;
	private boolean last;

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


	public List<Person> getPersonList() {
		return personList;
	}


	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}




	public Person getIdentifier() {
		return identifier;
	}


	public void setIdentifier(Person identifier) {
		this.identifier = identifier;
	}


	public Person getRecorder() {
		return recorder;
	}


	public void setRecorder(Person recorder) {
		this.recorder = recorder;
	}

	public List<Event> getEventList() {
		return eventList;
	}


	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	public boolean isFirst() {
		return first;
	}


	public void setFirst(boolean first) {
		this.first = first;
	}


	public boolean isLast() {
		return last;
	}


	public void setLast(boolean last) {
		this.last = last;
	}

	
	/**
	 * inicia as variáveis a serem utilizadas na tela de manutenção de ocorrências
	 */	
	@PostConstruct	
	public void init() {


		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");


		if(p != null) {
			Long id = Long.parseLong(p);		   
			event = sampleDAO.recupera(id);

		} else {		 
			eventList = sampleDAO.getEventOrderByDate();		 
		}

		if(event != null) {
			occurrenceList = event.getOccurrences();
		}

		taxonList = taxonDAO.pesquisa();			
		estableshimentList =  estableshimentDAO.pesquisa();			
		organismQuantityList =  organismQuantityTypeDAO.pesquisa();			
		dispositionList =  dispositionDAO.pesquisa();
		personList =  personDAO.getPersonOrderbyName();

	} 



	
	/*
	 * Abre a listagem de ocorrência após excluir uma ocorrência
	 */
	public String openDel() {
	

		event = sampleDAO.recupera(event.getId()); 

		occurrenceList = event.getOccurrences();

		return "../ocurrence/listOccurrance";

	}

	/*
	 * Abre a tela de ocorrência
	 */
	public String open(Event event) {

		occurrenceList = event.getOccurrences();

		return "../ocurrence/listOccurrance";

	}

	/*
	 * Redireciona para a tela de ocorrência
	 */
	public void open() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("listOccurrance.jsf?id=" + occurrence.getEvent().getId() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Atualiza os dados de ocorrência
	 */
	public void edit() {

		try {			 

			occurrenceDAO.atualiza(occurrence);
			event = sampleDAO.recupera(event.getId());

			occurrenceList = event.getOccurrences();

			Mensagem.adicionarMensagemSucesso("Ocorrência atualizada com sucesso!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}

	/*
	 * Remove os dados d eocorrência
	 */
	public void delete() {

		try {			 

			Occurrence occurrenceAux = new Occurrence();
			occurrenceAux.setId(occurrence.getId());

			occurrenceDAO.apaga(occurrenceAux);

			Mensagem.adicionarMensagemSucesso("Ocorrência removida com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_EXCLUIR);

		}
	}

	/*
	 * Volta para a listagem de ocorrência
	 */
	public String back() {

		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");

		Long id = Long.parseLong(p);

		if(id == 0) {
			id = event.getId();   
		}

		if(event == null) {
			event = sampleDAO.recupera(id); 
		}

		occurrenceList = event.getOccurrences();

		return "listOccurrance";
	}	

	/*
	 * Verifica se possue mais ocorrências a serem visualizadas na modal de edição e se estiver
	 * mostra a próxima.
	 */
	public void next() {

		try {
			long id = occurrence.getId();

			boolean achei = false;
			for(Occurrence o : occurrenceList) {

				if(achei) {
					occurrence = o;
					break;
				}

				if(o.getId() == id) {
					achei = true;
				}
			}

			verificarPosicao();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Verifica se possue mais ocorrências a serem visualizadas na modal de edição e se estiver
	 * mostra a próxima.
	 */
	public void previous() {

		try {
			long id = occurrence.getId();
			long idAnterior = 0; 
			boolean achei = false;
			for(Occurrence o : occurrenceList) {

				if(o.getId() == id) {
					achei = true;
					if(idAnterior != 0) {
						occurrence = occurrenceDAO.recupera(idAnterior);
					} else {
						occurrence = o;
					}

					break;
				}

				if(!achei) {
					idAnterior = o.getId();
				} 

			}

			verificarPosicao();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Na tela de edição de ocorrência verifica se o item é o último ou o primeiro, com 
	 * o objetivo de retirar o botão que permitir avançar ou retroceder para outra ocorrência
	 * 
	 */
	public void verificarPosicao(Occurrence occurrence) {

		Occurrence p = occurrenceList.get(0);

		if( occurrence.getId() == p.getId() ) {
			first = true;
			last = false;
			return;
		} else {
			first = true;
		}

		int pos = occurrenceList.size() - 1;
		p = occurrenceList.get(pos);

		if( occurrence.getId() == p.getId() ) {
			last = true;
			first = false;
		} else {
			last = true;
		}

	}

	/*
	 * Na tela de edição de ocorrência verifica se o item é o último ou o primeiro, com 
	 * o objetivo de retirar o botão que permitir avançar ou retroceder para outra ocorrência
	 * 
	 */
	public void verificarPosicao() {


		Occurrence p = occurrenceList.get(0);

		if( occurrence.getId() == p.getId() ) {
			first = true;
			last = false;
			return;
		} else {
			first = true;
		}

		int pos = occurrenceList.size() - 1;
		p = occurrenceList.get(pos);

		if( occurrence.getId() == p.getId() ) {
			last = true;
			first = false;
		} else {
			last = true;
		}
	}


}
