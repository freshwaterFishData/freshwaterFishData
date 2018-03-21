/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.MeasurementDAO;
import br.ufrj.macae.tic.modelo.dao.MeasurementMethodDAO;
import br.ufrj.macae.tic.modelo.dao.OccurrenceDAO;
import br.ufrj.macae.tic.modelo.dao.OrganismDAO;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.persistence.entity.Measurement;
import br.ufrj.macae.tic.persistence.entity.MeasurementMethod;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.Organism;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class MeasurementMB implements Serializable {		



	/**
	 * 
	 */
	private static final long serialVersionUID = 8930371778192242404L;

	@Inject
	private Measurement measurement;

	private Organism organism;	

	@Inject
	private OrganismDAO organismDAO;	

	@Inject
	private MeasurementDAO measurementDAO;

	private Occurrence occurrence;

	@Inject
	private OccurrenceDAO occurrenceDAO;	


	@Inject
	private PersonDAO personDAO;

	@Inject
	private MeasurementMethodDAO measurementMethodDAO;

	private List<Measurement> measurementList;

	private List<Person> personList;

	private List<Organism> organismList;

	private List<MeasurementMethod> measurementMethodList;

	public Organism getOrganism() {
		return organism;
	}

	public void setOrganism(Organism organism) {
		this.organism = organism;
	}



	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	public Occurrence getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Occurrence occurrence) {
		this.occurrence = occurrence;
	}


	public List<Measurement> getMeasurementList() {
		return measurementList;
	}

	public void setMeasurementList(List<Measurement> measurementList) {
		this.measurementList = measurementList;
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



	public List<MeasurementMethod> getMeasurementMethodList() {
		return measurementMethodList;
	}

	public void setMeasurementMethodList(List<MeasurementMethod> measurementMethodList) {
		this.measurementMethodList = measurementMethodList;
	}



	/*
	 * inicia as variáveis a serem utilizadas na tela de manutenção dos dados de medidas
	 */
	@PostConstruct	
	public void init() {


		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");

		Long id = Long.parseLong(p);

		organism = organismDAO.recupera(id);	 

		measurementList = organism.getMeasurement();

		personList =  personDAO.getPersonOrderbyName();

		measurementMethodList = measurementMethodDAO.pesquisa();

	} 

	/*
	 * Salva os dados de medidas 
	 */
	public void save() {

		try {

			measurement.setOrganism(organism);   
			measurementDAO.insere(measurement);

			loadOrganism();

			Mensagem.adicionarMensagemSucesso("Medida cadastrada com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}


	/*
	 * Salva os dados de medidas 
	 */
	public void edit() {

		try {			 


			measurementDAO.atualiza(measurement);

			loadOrganism();

			Mensagem.adicionarMensagemSucesso("Medida atualizada com sucesso!");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}


	public void delete() {

		try {			 

			Measurement measurementAux = new Measurement();
			measurementAux.setId(measurement.getId());

			measurementDAO.apaga(measurementAux);

			Mensagem.adicionarMensagemSucesso("Medida removida com sucesso!");



		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_EXCLUIR);

		}
	}

	public void loadOrganism() {
		organism  = organismDAO.recupera(organism.getId());
		measurementList = organism.getMeasurement();
	}

	public String back() {


		organismList = organism.getOccurrence().getOrganismList();

		return "listOrganism";
	}	

	public String openDel() {

		organism  = organismDAO.recupera(organism.getId());
		measurementList = organism.getMeasurement();

		return "listMeasurement";
	}


}
