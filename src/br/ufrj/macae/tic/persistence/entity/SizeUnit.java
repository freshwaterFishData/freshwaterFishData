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
@Table(name = "sizeUnit", catalog = "peld")
public class SizeUnit implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2888104418656465029L;

	private int id;
		
	private String sizeUnit;
	
	private List<Event> events;
	

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	@Column(name = "sizeUnit")
	public String getSizeUnit() {
		return this.sizeUnit;
	}

	public void setSizeUnit(String sizeUnit) {
		this.sizeUnit = sizeUnit;
	}

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sizeUnit")
	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
