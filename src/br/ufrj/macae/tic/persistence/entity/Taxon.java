package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Taxon generated by hbm2java
 */
@Entity
@Table(name = "taxon", catalog = "peld")
public class Taxon implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6017657345817428885L;
	
	private long id;
	private String scientificName;
	private String order;
	private String family;
	private String genus;
	private String specificEpithet;
	
	//private List<Occurrence> occurrences;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "scientificName", nullable = false)
	public String getScientificName() {
		return this.scientificName;
	}

	public void setScientificName(String scientificName) {
		this.scientificName = scientificName;
	}

	@Column(name = "ordem")
	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Column(name = "family", nullable = false)
	public String getFamily() {
		return this.family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	@Column(name = "genus", nullable = false)
	public String getGenus() {
		return this.genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}
	
	/*
	@Column(name = "kingdom", length = 60)
	public String getKingdom() {
		return this.kingdom;
	}

	public void setKingdom(String kingdom) {
		this.kingdom = kingdom;
	}*/
	

	@Column(name = "specificEpithet", length = 60)
	public String getSpecificEpithet() {
		return this.specificEpithet;
	}

	public void setSpecificEpithet(String specificEpithet) {
		this.specificEpithet = specificEpithet;
	}

	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "taxon")
	public List<Occurrence> getOccurrences() {
		return this.occurrences;
	}

	public void setOccurrences(List<Occurrence> occurrences) {
		this.occurrences = occurrences;
	}*/

}
