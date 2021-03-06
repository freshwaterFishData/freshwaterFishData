package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * County generated by hbm2java
 */
@Entity
@Table(name = "county", catalog = "peld")
public class County implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7845716342921339515L;
	private long id;
	private String county;
	
	private String stateProvince;
	private String country;
	private String continent;
	
	private List<WaterBody> waterBodyList;

	public County() {
	}

	public County(String county) {
		this.county = county;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "county", nullable = false, length = 60)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "county")
	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}*/
	
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "county")
	public List<WaterBody> getWaterBodyList() {
		return this.waterBodyList;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public void setWaterBodyList(List<WaterBody> waterBodyList) {
		this.waterBodyList = waterBodyList;
	}

}
