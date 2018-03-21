package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "embryonicPhase", catalog = "peld")
public class EmbryonicPhase implements java.io.Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5535020078204476583L;
	
	private long id;
	private String lifestage;
	private Integer fertility;
	private Integer lineWeight;
	private String unit;
	
	private Organism organism;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
		
	@Column(name = "lifestage", nullable = false, length = 45)
	public String getLifestage() {
		return this.lifestage;
	}

	public void setLifestage(String lifestage) {
		this.lifestage = lifestage;
	}

	@Column
	public Integer getFertility() {
		return fertility;
	}

	public void setFertility(Integer fertility) {
		this.fertility = fertility;
	}

	@Column
	public Integer getLineWeight() {
		return lineWeight;
	}
	
	public void setLineWeight(Integer organismlineWeight) {
		this.lineWeight = organismlineWeight;
	}

	@Column
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organismId")
	public Organism getOrganism() {
		return organism;
	}
	
	public void setOrganism(Organism organism) {
		this.organism = organism;
	}	

}
