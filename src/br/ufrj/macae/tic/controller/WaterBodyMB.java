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
import br.ufrj.macae.tic.modelo.dao.CountyDAO;
import br.ufrj.macae.tic.modelo.dao.HabitatDAO;
import br.ufrj.macae.tic.modelo.dao.MunicipalityDAO;
import br.ufrj.macae.tic.modelo.dao.OccurrenceDAO;
import br.ufrj.macae.tic.modelo.dao.OrganismDAO;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.modelo.dao.WaterBodyDAO;
import br.ufrj.macae.tic.persistence.entity.County;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Habitat;
import br.ufrj.macae.tic.persistence.entity.Municipality;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.Organism;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.persistence.entity.WaterBody;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class WaterBodyMB implements Serializable {		

	private static final long serialVersionUID = 3735988024311627776L;

	@Inject
	private WaterBody waterBody;

	@Inject
	private WaterBodyDAO waterBodyDAO;

	@Inject
	private HabitatDAO habitatDAO;

	@Inject
	private CountyDAO countyDAO;

	@Inject
	private MunicipalityDAO municipalityDAO;

	private List<WaterBody> waterBodyList;
	private List<Habitat> habitatList;
	private List<County> countyList;
	private List<Municipality> municipalityList; 



	public WaterBody getWaterBody() {
		return waterBody;
	}

	public void setWaterBody(WaterBody waterBody) {
		this.waterBody = waterBody;
	}

	public List<WaterBody> getWaterBodyList() {
		return waterBodyList;
	}

	public void setWaterBodyList(List<WaterBody> waterBodyList) {
		this.waterBodyList = waterBodyList;
	}

	public List<Habitat> getHabitatList() {
		return habitatList;
	}

	public void setHabitatList(List<Habitat> habitatList) {
		this.habitatList = habitatList;
	}


	public List<County> getCountyList() {
		return countyList;
	}

	public void setCountyList(List<County> countyList) {
		this.countyList = countyList;
	}



	public List<Municipality> getMunicipalityList() {
		return municipalityList;
	}

	public void setMunicipalityList(List<Municipality> municipalityList) {
		this.municipalityList = municipalityList;
	}

	/*
	 * Este método inicia as varáveis a serem usadas na tela de waterBody
	 */
	@PostConstruct	
	public void init() {		 
		waterBodyList = waterBodyDAO.pesquisa();
		habitatList = habitatDAO.pesquisa();
		countyList = countyDAO.pesquisa();
		municipalityList = municipalityDAO.pesquisa();
	} 

	/*
	 * Salva os dados de waterBody
	 */
	public void save() {

		try {

			waterBodyDAO.insere(waterBody);

			Mensagem.adicionarMensagemSucesso("Corpo D'Água cadastrado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();
			Mensagem.adicionarMensagemErro(Util.ERRO_INSERIR);
		}

	}

	public String openDel() {

		return "../ocurrence/listOccurrance";

	}

	/*
	 * Edita os dados
	 */
	public void edit() {

		try {			 

			waterBodyDAO.atualiza(waterBody);

			Mensagem.adicionarMensagemSucesso("Corpo D'Água atualizado com sucesso!");

		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}

	/*
	 * Remove os dados
	 */
	public void delete() {

		try {			 

			WaterBody waterBody = new WaterBody();
			waterBody.setId(this.waterBody.getId());

			waterBodyDAO.apaga(waterBody);

			waterBodyList.remove(this.waterBody);

			Mensagem.adicionarMensagemSucesso("Item Removido com sucesso.");


		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_EXCLUIR);

		}
	}



}
