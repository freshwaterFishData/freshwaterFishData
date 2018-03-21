package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonOccurrence generated by hbm2java
 */
@Entity
@Table(name = "person_occurrence", catalog = "peld")
public class PersonOccurrence implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5797824484844987032L;
	private long id;
	private Occurrence occurrence;
	private long personId;

	public PersonOccurrence() {
	}

	public PersonOccurrence(Occurrence occurrence, long personId) {
		this.occurrence = occurrence;
		this.personId = personId;
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
	@JoinColumn(name = "occurrenceId", nullable = false)
	public Occurrence getOccurrence() {
		return this.occurrence;
	}

	public void setOccurrence(Occurrence occurrence) {
		this.occurrence = occurrence;
	}

	@Column(name = "personId", nullable = false)
	public long getPersonId() {
		return this.personId;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

}