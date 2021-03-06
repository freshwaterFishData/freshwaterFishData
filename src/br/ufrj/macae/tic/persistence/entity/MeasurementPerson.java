package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * MeasurementPerson generated by hbm2java
 */
@Entity
@Table(name = "measurement_person", catalog = "peld")
public class MeasurementPerson implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1240472700331936766L;
	private long id;
	private Measurement measurement;
	private Person person;
	private Date measurementDeterminedDate;

	public MeasurementPerson() {
	}

	public MeasurementPerson(Measurement measurement, Person person, Date measurementDeterminedDate) {
		this.measurement = measurement;
		this.person = person;
		this.measurementDeterminedDate = measurementDeterminedDate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measurementId", nullable = false)
	public Measurement getMeasurement() {
		return this.measurement;
	}

	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measurementDeterminedById", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "measurementDeterminedDate", nullable = false, length = 10)
	public Date getMeasurementDeterminedDate() {
		return this.measurementDeterminedDate;
	}

	public void setMeasurementDeterminedDate(Date measurementDeterminedDate) {
		this.measurementDeterminedDate = measurementDeterminedDate;
	}

}
