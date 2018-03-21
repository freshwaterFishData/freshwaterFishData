/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.CountyDAO;
import br.ufrj.macae.tic.modelo.dao.HabitatDAO;
import br.ufrj.macae.tic.modelo.dao.MunicipalityDAO;
import br.ufrj.macae.tic.modelo.dao.SampleDAO;
import br.ufrj.macae.tic.modelo.dao.SampleProtocolDAO;
import br.ufrj.macae.tic.modelo.dao.SizeUnitDAO;
import br.ufrj.macae.tic.modelo.dao.SubprojectDAO;
import br.ufrj.macae.tic.modelo.dao.WaterBodyDAO;
import br.ufrj.macae.tic.persistence.entity.County;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Habitat;
import br.ufrj.macae.tic.persistence.entity.Municipality;
import br.ufrj.macae.tic.persistence.entity.SamplingProtocol;
import br.ufrj.macae.tic.persistence.entity.SizeUnit;
import br.ufrj.macae.tic.persistence.entity.WaterBody;
import br.ufrj.macae.tic.util.Util;


@Named
@SessionScoped
@ManagedBean
public class InsertSampleMB implements Serializable {	


	/**
	 * 
	 */
	private static final long serialVersionUID = -8023212851823352562L;

	@Inject
	private Event event;

	private Event eventAux;

	@Inject
	private SampleDAO sampleDAO;

	@Inject
	private SampleProtocolDAO samplingProtocolDAO;

	@Inject
	private CountyDAO countyDAO; 

	@Inject
	private  MunicipalityDAO municipalityDAO; 

	@Inject
	private HabitatDAO habitatDAO;

	@Inject
	private WaterBodyDAO waterBodyDAO;

	@Inject
	private SizeUnitDAO sizeUnitDAO;

	@Inject
	private SubprojectDAO subprojectDAO;		


	private List<SamplingProtocol>  samplingProtocolAList;	
	private List<SizeUnit> sizeUnitListAList;	
	private List<County>  countyListAList;		
	private List<Municipality> municipalityListAList;	
	private List<Habitat> habitatListAList;			
	private List<WaterBody> waterBodyListAList;

	private List<Event> eventList;

	private String sigla;

	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getEventList() {
		return eventList;
	}


	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}


	public Event getEventAux() {
		return eventAux;
	}


	public void setEventAux(Event eventAux) {
		this.eventAux = eventAux;
	}


	public List<SamplingProtocol> getSamplingProtocolAList() {
		return samplingProtocolAList;
	}


	public void setSamplingProtocolAList(List<SamplingProtocol> samplingProtocolAList) {
		this.samplingProtocolAList = samplingProtocolAList;
	}


	public List<SizeUnit> getSizeUnitListAList() {
		return sizeUnitListAList;
	}


	public void setSizeUnitListAList(List<SizeUnit> sizeUnitListAList) {
		this.sizeUnitListAList = sizeUnitListAList;
	}


	public List<County> getCountyListAList() {
		return countyListAList;
	}


	public void setCountyListAList(List<County> countyListAList) {
		this.countyListAList = countyListAList;
	}


	public List<Municipality> getMunicipalityListAList() {
		return municipalityListAList;
	}


	public void setMunicipalityListAList(List<Municipality> municipalityListAList) {
		this.municipalityListAList = municipalityListAList;
	}


	public List<Habitat> getHabitatListAList() {
		return habitatListAList;
	}


	public void setHabitatListAList(List<Habitat> habitatListAList) {
		this.habitatListAList = habitatListAList;
	}


	public List<WaterBody> getWaterBodyListAList() {
		return waterBodyListAList;
	}


	public void setWaterBodyListAList(List<WaterBody> waterBodyListAList) {
		this.waterBodyListAList = waterBodyListAList;
	}


	/**
	 * Formata o número de campo da tela de inserção de dados de coleta
	 */
	public void getSigla() {

		event.setSubproject(subprojectDAO.recupera(event.getSubproject().getId()));
		Date d = event.getEventDate();

		if(d == null) {
			return;
		}

		SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
		String dataFormatada = formato.format(d);

		long l = sampleDAO.getMaxEvent();

		sigla = event.getSubproject().getSigla() + dataFormatada + l; 

		event.setEventId(sigla);

	}


	/*
	 * inicia as variáveis a serem utilizadas na tela de inserção de dados de coleta
	 */
	@PostConstruct
	public void init() {

		samplingProtocolAList = samplingProtocolDAO.pesquisa();		
		sizeUnitListAList = sizeUnitDAO.pesquisa();		
		countyListAList = countyDAO.pesquisa();			
		municipalityListAList = municipalityDAO.pesquisa();		
		habitatListAList = habitatDAO.pesquisa();				
		waterBodyListAList = waterBodyDAO.getWaterBodyOrder();

	}

	/*
	 * Abre a tela de inserção de dados de coleta 
	 */
	public String open() {

		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("subprojectId");

		Long id = Long.parseLong(p);

		event.getSubproject().setId(id);
		event.setSampleEffort("");

		return "insert";


	}


	/*
	 * Salva os dados de coleta
	 */
	public void save() {

		try {

			List<Event> l = sampleDAO.getEventByEventId(event.getEventId());
			if(!l.isEmpty()) {
				Mensagem.adicionarMensagemErro("O número de campo ja se encontra cadastrado.");

			} else {
				sampleDAO.insere(event);

				Mensagem.adicionarMensagemSucesso();
			}


		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}

	public String edit(Event event) {

		this.event = event;


		return "insert";

	}

	/*
	 * Redireciona para a tela de ocorrência relacionada ao evento cadastrado
	 */
	public void openOccurrence() {

		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../ocurrence/listOccurrance.jsf?id=" + event.getId() );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 * Limpa os dados do formulário de inserção de dados de ocorrência
	 */
	public String clean() {

		eventAux = new Event();
		eventAux.setSubproject(event.getSubproject());

		event = new Event();	   
		event.setSubproject(eventAux.getSubproject());	     
		event.setSamplingProtocol(new SamplingProtocol());
		event.setSizeUnit(new SizeUnit());
		event.setWaterBody(new WaterBody());	

		return "insert";

	}

	/*
	 * Popula a lista de WaterBody a ser usada na tela de inserção de dados de coleta
	 */
	public void populateWaterBody() {

		waterBodyListAList = waterBodyDAO.getWaterBodyOrder();
	}



}
