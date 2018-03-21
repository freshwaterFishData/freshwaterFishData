package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MeasurementMethod generated by hbm2java
 */
@Entity
@Table(name = "measurementMethod", catalog = "peld")
public class MeasurementMethod implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1872646402452834872L;
	private long id;
	private String measurementMethod;
	private List<Measurement> measurements;

	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "measurementMethod", length = 120)
	public String getMeasurementMethod() {
		return this.measurementMethod;
	}

	public void setMeasurementMethod(String measurementMethod) {
		this.measurementMethod = measurementMethod;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measurementMethod")
	public List<Measurement> getMeasurements() {
		return this.measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

}
