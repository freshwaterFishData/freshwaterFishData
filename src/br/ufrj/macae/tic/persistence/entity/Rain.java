package br.ufrj.macae.tic.persistence.entity;
// Generated 26/05/2017 12:44:14 by Hibernate Tools 5.2.1.Final

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Rain generated by hbm2java
 */
@Entity
@Table(name = "rain", catalog = "peld")
public class Rain implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2466553315344513656L;
	
	private long id;
	private float rainValue;
	private Date rainDate;
	private Date rainTime;
	
	private List<Air> airs;
	private List<Water> waters;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "rainValue", nullable = false, precision = 12, scale = 0)
	public float getRainValue() {
		return this.rainValue;
	}

	public void setRainValue(float rainValue) {
		this.rainValue = rainValue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "rainDate", nullable = false, length = 10)
	public Date getRainDate() {
		return this.rainDate;
	}

	public void setRainDate(Date rainDate) {
		this.rainDate = rainDate;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "rainTime", nullable = false, length = 8)
	public Date getRainTime() {
		return this.rainTime;
	}

	public void setRainTime(Date rainTime) {
		this.rainTime = rainTime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rain")
	public List<Air> getAirs() {
		return this.airs;
	}

	public void setAirs(List<Air> airs) {
		this.airs = airs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rain")
	public List<Water> getWaters() {
		return this.waters;
	}

	public void setWaters(List<Water> waters) {
		this.waters = waters;
	}

}