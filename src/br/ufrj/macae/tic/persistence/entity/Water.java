package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import javax.inject.Inject;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Water generated by hbm2java
 */
@Entity
@Table(name = "water", catalog = "peld")
public class Water implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8120905454384770302L;
	
	private int id;
	
	@Inject
	private Event event;
	
	@Inject
	private Rain rain;
	
	private Float salinity;
	private Float temperature;
	private String temperatureUnit;
	private Float dissolvedOxigenPercentual;
	private Float dissolvedOxigenMl;
	private Float ph;
	private Float dissolvedOrganicCaborn;
	private String dissolvedOrganicCabornUnit;
	private Float nitrogen;
	private String nitrogenUnit;
	private Float clorophyll;
	private String clorophyllUnit;
	private Float depth;
	private String depthUnit;
	private Float sechiDepth;
	private String sechiDepthUnit;
	private Float conductivity;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "eventId", nullable = false)
	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rainId", nullable = false)
	public Rain getRain() {
		return this.rain;
	}

	public void setRain(Rain rain) {
		this.rain = rain;
	}

	@Column(name = "salinity", precision = 12, scale = 0)
	public Float getSalinity() {
		return this.salinity;
	}

	public void setSalinity(Float salinity) {
		this.salinity = salinity;
	}

	@Column(name = "temperature", precision = 12, scale = 0)
	public Float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	@Column(name = "temperatureUnit", length = 45)
	public String getTemperatureUnit() {
		return this.temperatureUnit;
	}

	public void setTemperatureUnit(String temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}

	@Column(name = "dissolvedOxigenPercentual", precision = 12, scale = 0)
	public Float getDissolvedOxigenPercentual() {
		return this.dissolvedOxigenPercentual;
	}

	public void setDissolvedOxigenPercentual(Float dissolvedOxigenPercentual) {
		this.dissolvedOxigenPercentual = dissolvedOxigenPercentual;
	}

	@Column(name = "dissolvedOxigenMl", precision = 12, scale = 0)
	public Float getDissolvedOxigenMl() {
		return this.dissolvedOxigenMl;
	}

	public void setDissolvedOxigenMl(Float dissolvedOxigenMl) {
		this.dissolvedOxigenMl = dissolvedOxigenMl;
	}

	@Column(name = "ph", precision = 12, scale = 0)
	public Float getPh() {
		return this.ph;
	}

	public void setPh(Float ph) {
		this.ph = ph;
	}

	@Column(name = "dissolvedOrganicCaborn", precision = 12, scale = 0)
	public Float getDissolvedOrganicCaborn() {
		return this.dissolvedOrganicCaborn;
	}

	public void setDissolvedOrganicCaborn(Float dissolvedOrganicCaborn) {
		this.dissolvedOrganicCaborn = dissolvedOrganicCaborn;
	}

	@Column(name = "dissolvedOrganicCabornUnit", length = 45)
	public String getDissolvedOrganicCabornUnit() {
		return this.dissolvedOrganicCabornUnit;
	}

	public void setDissolvedOrganicCabornUnit(String dissolvedOrganicCabornUnit) {
		this.dissolvedOrganicCabornUnit = dissolvedOrganicCabornUnit;
	}

	@Column(name = "nitrogen", precision = 12, scale = 0)
	public Float getNitrogen() {
		return this.nitrogen;
	}

	public void setNitrogen(Float nitrogen) {
		this.nitrogen = nitrogen;
	}

	@Column(name = "nitrogenUnit", length = 45)
	public String getNitrogenUnit() {
		return this.nitrogenUnit;
	}

	public void setNitrogenUnit(String nitrogenUnit) {
		this.nitrogenUnit = nitrogenUnit;
	}

	@Column(name = "clorophyll", precision = 12, scale = 0)
	public Float getClorophyll() {
		return this.clorophyll;
	}

	public void setClorophyll(Float clorophyll) {
		this.clorophyll = clorophyll;
	}

	@Column(name = "clorophyllUnit", length = 45)
	public String getClorophyllUnit() {
		return this.clorophyllUnit;
	}

	public void setClorophyllUnit(String clorophyllUnit) {
		this.clorophyllUnit = clorophyllUnit;
	}

	@Column(name = "depth", precision = 12, scale = 0)
	public Float getDepth() {
		return this.depth;
	}

	public void setDepth(Float depth) {
		this.depth = depth;
	}

	@Column(name = "depthUnit", length = 45)
	public String getDepthUnit() {
		return this.depthUnit;
	}

	public void setDepthUnit(String depthUnit) {
		this.depthUnit = depthUnit;
	}

	@Column(name = "sechiDepth", precision = 12, scale = 0)
	public Float getSechiDepth() {
		return this.sechiDepth;
	}

	public void setSechiDepth(Float sechiDepth) {
		this.sechiDepth = sechiDepth;
	}

	@Column(name = "sechiDepthUnit", length = 45)
	public String getSechiDepthUnit() {
		return this.sechiDepthUnit;
	}

	public void setSechiDepthUnit(String sechiDepthUnit) {
		this.sechiDepthUnit = sechiDepthUnit;
	}

	@Column(name = "conductivity", precision = 12, scale = 0)
	public Float getConductivity() {
		return this.conductivity;
	}

	public void setConductivity(Float conductivity) {
		this.conductivity = conductivity;
	}

}
