/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.SampleDAO;
import br.ufrj.macae.tic.modelo.dao.WaterDAO;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Water;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
@RequestScoped
@ManagedBean
public class InsertWaterMB implements Serializable {	


	/**
	 * 
	 */
	private static final long serialVersionUID = 2746627800531600946L;

	@Inject
	private Water water;

	@Inject
	private Event event;


	@Inject
	private SampleDAO sampleDAO;

	@Inject
	private WaterDAO waterDAO;


	private List<Water> waterList;


	public Water getWater() {
		return water;
	}


	public void setWater(Water water) {
		this.water = water;
	}


	public Event getEvent() {
		return event;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public List<Water> getWaterList() {
		return waterList;
	}


	public void setWaterList(List<Water> waterList) {
		this.waterList = waterList;
	}



	@PostConstruct
	public void init() {


	}



	/*
	 * Salva os dados de àgua
	 */
	public void save() {

		try {


			String p = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("eventId");

			Long id = Long.parseLong(p);

			event.setId(id);

			water.setEvent(event);
			waterDAO.insere(water);

			Mensagem.adicionarMensagemSucesso("Dados da água cadastrados com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}




	/*
	 * Abre a tela de àgua
	 */
	public String open() {

		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("eventId");

		Long id = Long.parseLong(p);		 

		event.setId(id);	

		// initLists();

		return "insertWater";

	}

}
