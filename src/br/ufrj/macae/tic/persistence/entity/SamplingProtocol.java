package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SamplingProtocol generated by hbm2java
 */
@Entity
@Table(name = "samplingProtocol", catalog = "peld")
public class SamplingProtocol implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7559853492097602465L;
	
	private long id;
	private String samplingProtocol;
	
	private List<Event> events;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "samplingProtocol", nullable = false, length = 60)
	public String getSamplingProtocol() {
		return this.samplingProtocol;
	}

	public void setSamplingProtocol(String samplingProtocol) {
		this.samplingProtocol = samplingProtocol;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "samplingProtocol")
	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
