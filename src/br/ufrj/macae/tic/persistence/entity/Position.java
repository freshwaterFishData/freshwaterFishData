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
 * Position generated by hbm2java
 */
@Entity
@Table(name = "position", catalog = "peld")
public class Position implements java.io.Serializable {

	private long id;
	private String positionName;
	private List<Person> persons;

	

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "positionName", nullable = false, length = 120)
	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
