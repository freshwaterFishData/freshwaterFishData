/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.TaxonDAO;
import br.ufrj.macae.tic.persistence.entity.Taxon;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class TaxonMB implements Serializable {		

	private static final long serialVersionUID = 3735988024311627776L;

	@Inject
	private Taxon taxon;	

	@Inject
	private TaxonDAO taxonDAO;	

	private List<Taxon> taxonList;



	public Taxon getTaxon() {
		return taxon;
	}

	public void setTaxon(Taxon taxon) {
		this.taxon = taxon;
	}

	public TaxonDAO getTaxonDAO() {
		return taxonDAO;
	}

	public void setTaxonDAO(TaxonDAO taxonDAO) {
		this.taxonDAO = taxonDAO;
	}

	public List<Taxon> getTaxonList() {
		return taxonList;
	}

	public void setTaxonList(List<Taxon> taxonList) {
		this.taxonList = taxonList;
	}

	@PostConstruct	
	public void init() {


		taxonList = taxonDAO.pesquisa();


	} 


	/*
	 * Salva os dados
	 */
	public void save() {

		try {

			taxonDAO.insere(taxon);
			taxonList = taxonDAO.pesquisa();	

			taxon = new Taxon();

			Mensagem.adicionarMensagemSucesso("Taxon cadastrado com sucesso!");

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
	 * Edita os dados
	 */
	public void edit() {

		try {			 


			taxonDAO.atualiza(taxon);
			taxonList = taxonDAO.pesquisa();	

			taxon = new Taxon();

			Mensagem.adicionarMensagemSucesso("Taxon atualizado com sucesso!");



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);

		}
	}

	/*
	 * Exclui os dados
	 */
	public void delete() {

		try {			 

			taxonDAO.apaga(taxon);

			taxonList = taxonDAO.pesquisa();	

			Mensagem.adicionarMensagemSucesso("Taxon removido com sucesso.");

		} catch (Exception e) {
			e.printStackTrace();

			Mensagem.adicionarMensagemSucesso(Util.ERRO_EXCLUIR);

		}
	}



}
