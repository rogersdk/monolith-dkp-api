package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "loot")
public class Loot implements Serializable {

	private static final long serialVersionUID = -7595346736100979483L;

	private Long id;
	private Dkp dkp;
	private Date date;
	private Mob from;
	private Integer wowheadItemId;

	public Loot() {
		// Default constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.PERSIST)
	public Dkp getDkp() {
		return dkp;
	}

	public void setDkp(Dkp dkp) {
		this.dkp = dkp;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne(cascade = CascadeType.PERSIST)
	public Mob getFrom() {
		return from;
	}

	public void setFrom(Mob from) {
		this.from = from;
	}

	@Column(name = "wowhead_item_id")
	public Integer getWowheadItemId() {
		return wowheadItemId;
	}

	public void setWowheadItemId(Integer wowheadItemId) {
		this.wowheadItemId = wowheadItemId;
	}

	@Override
	public String toString() {
		return "Loot [id=" + id + ", dkp=" + dkp + ", date=" + date + ", from=" + from + ", wowheadItemId="
				+ wowheadItemId + "]";
	}
}
