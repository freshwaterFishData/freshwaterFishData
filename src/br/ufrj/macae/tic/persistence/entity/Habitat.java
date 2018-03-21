package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name = "habitat", catalog = "peld")
public class Habitat implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5845238920610750943L;

	private int id;
		
	private String habitat;
	
	private List<WaterBody> waterBodyList;
	
	//private List<Event> events;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "habitat")
	public List<WaterBody> getwaterBodyList() {
		return this.waterBodyList;
	}

	public void setwaterBodyList(List<WaterBody> waterBodyList) {
		this.waterBodyList = waterBodyList;
	}
	

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	@Column(name = "habitat")
	public String getHabitat() {
		return this.habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	
	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "habitat")
	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	} */

}