/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufrj.macae.tic.controller;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufrj.macae.tic.mensagem.Mensagem;
import br.ufrj.macae.tic.modelo.dao.OccurrenceDAO;
import br.ufrj.macae.tic.modelo.dao.SubprojectDAO;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.Subproject;


@Named
@SessionScoped
@ManagedBean
public class ReportMB implements Serializable {	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8023212851823352562L;

	@Inject
	private Occurrence occurrence;
	
	@Inject
	private OccurrenceDAO occurrenceDAO;
	
	@Inject
	private SubprojectDAO subprojectDAO;		
		
	
	private List<Subproject> subProjectList;
	
	private List<Occurrence> occurrenceList;
	
	private Date finalDate;

	public List<Subproject> getSubProjectList() {
		return subProjectList;
	}


	public void setSubProjectList(List<Subproject> subProjectList) {
		this.subProjectList = subProjectList;
	}

		
	public Date getFinalDate() {
		return finalDate;
	}


	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

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


   @PostConstruct
   public void init() {
 		subProjectList = subprojectDAO.pesquisa(); 		
   }
		
  
   /**
    * Pesquisar ocorrência por data
    */
   public void searchOccurrence() {	   
	   
	   try {
		   occurrenceList = occurrenceDAO.getOccurrenceByDate(occurrence, finalDate);
	   } catch (Exception e) {
		 e.printStackTrace();
		 Mensagem.adicionarMensagemErro();
	   }
	   
   }
   
  

}
