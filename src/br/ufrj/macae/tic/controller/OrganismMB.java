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
import br.ufrj.macae.tic.modelo.dao.OccurrenceDAO;
import br.ufrj.macae.tic.modelo.dao.OrganismDAO;
import br.ufrj.macae.tic.modelo.dao.PersonDAO;
import br.ufrj.macae.tic.persistence.entity.Event;
import br.ufrj.macae.tic.persistence.entity.Occurrence;
import br.ufrj.macae.tic.persistence.entity.Organism;
import br.ufrj.macae.tic.persistence.entity.Person;
import br.ufrj.macae.tic.util.Util;


@Named
@ViewScoped
public class OrganismMB implements Serializable {		
	
	private static final long serialVersionUID = 3735988024311627776L;
	
	@Inject
	private Organism organism;	
	
	private Occurrence occurrence;
			
	@Inject
	private OrganismDAO organismDAO;	
	
	@Inject
	private OccurrenceDAO occurrenceDAO;	
	
	@Inject
	private PersonDAO personDAO;
		
	private List<Organism> organismList;
	
	private List<Person> personList;
	
  
    public Organism getOrganism() {
		return organism;
    }

	public void setOrganism(Organism organism) {
		this.organism = organism;
	}



	public Occurrence getOccurrence() {
		return occurrence;
	}



	public void setOccurrence(Occurrence occurrence) {
		this.occurrence = occurrence;
	}



	public List<Organism> getOrganismList() {
		return organismList;
	}



	public void setOrganismList(List<Organism> organismList) {
		this.organismList = organismList;
	}



	public List<Person> getPersonList() {
		return personList;
	}



	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}



@PostConstruct	
   public void init() {
	
	
     String p = FacesContext.getCurrentInstance().getExternalContext()
				   .getRequestParameterMap().get("id");
		   
	 Long id = Long.parseLong(p);
		   
     occurrence = occurrenceDAO.recupera(id);	 
	  
	 organismList = occurrence.getOrganismList();
	 
	 
	 //personList =  personDAO.getPersonOrderbyName();		
	 
   } 

public void save() {
	   
	   try {
		   		   
		organism.setOccurrence(occurrence);   
		organismDAO.insere(organism);
		
		loadOccurrance();
				
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
   
   public String open(Event event) {
		 
		 organismList = occurrence.getOrganismList();
		 
		 return "../ocurrence/listOrganism";
		   
   }
   
   public void edit() {
	   
		 try {			 
			 
		     
			organismDAO.atualiza(organism);
			
			loadOccurrance();
							
			Mensagem.adicionarMensagemSucesso("Organismo atualizado com sucesso!");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			Mensagem.adicionarMensagemErro(Util.ERRO_ATUALIZAR);
			 
		}
}
 
 public void delete() {
	   
		 try {			 
			 
			 organismDAO.apaga(organism);
			 
			 loadOccurrance();
							

				Mensagem.adicionarMensagemSucesso("Removido com sucesso.");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			Mensagem.adicionarMensagemErro("Ocorreu um erro inesperado, por favor, contacte nosso suporte.");
			 
		}
}

private void loadOccurrance() {
	occurrence = occurrenceDAO.recupera(occurrence.getId());
	 organismList = occurrence.getOrganismList();
}
 
 public String back() {
	 	 
	 try {
		FacesContext.getCurrentInstance().getExternalContext().redirect("listOccurrance.jsf?id=" + occurrence.getEvent().getId() );
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		 
	return "listOccurrance";
 }	
 
 
}
