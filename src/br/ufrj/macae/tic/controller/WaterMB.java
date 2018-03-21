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
import br.ufrj.macae.tic.modelo.dao.SampleDAO;
import br.ufrj.macae.tic.modelo.dao.WaterDAO;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Water;


@Named
@ViewScoped
public class WaterMB implements Serializable {	


	/**
	 * 
	 */
	private static final long serialVersionUID = 6094420815602636425L;

	@Inject
	private Water water;

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

	
	/**
	 * Este método inicia as varáveis a serem usadas na tela de àgua
	 */
	@PostConstruct	
	public void init() {


		String p = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");

		Long id = Long.parseLong(p);

		event = sampleDAO.recupera(id);


		waterList = event.getWaters();


	} 


	/**
	 * Abre a tela de àgua após exclusão dos dados de àgua
	 * @return
	 */
	public String openDel() {

		event = sampleDAO.recupera(event.getId()); 

		waterList = event.getWaters();

		return "../water/listWater";

	}

	/*
	 * Abre a tela de ocorrência
	 */
	public String open(Event event) {	

		waterList = event.getWaters();

		return "../ocurrence/listOccurrance";

	}

	/*
	 * Edita os dados da àgua
	 */
	public void edit() {

		try {			 


			waterDAO.atualiza(water);

			Mensagem.adicionarMensagemSucesso("Dados da água atualizados com sucesso!");

			//open(occurrence.getEvent());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro();

		}
	}

	/*
	 * Remove os dados da àgua
	 */
	public void delete() {

		try {			 

			Water waterAux = new Water();
			waterAux.setId(water.getId());

			waterDAO.apaga(waterAux);


			Mensagem.adicionarMensagemSucesso("Dados da água removidos com sucesso!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro();

		}
	}

	/*
	 *Volta para a listagem de dados de àgua 
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


		waterList = event.getWaters();

		return "listWater";
	}	





}
