package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;
import java.util.Date;



public class Loot implements Serializable {

	private static final long serialVersionUID = -7595346736100979483L;

	private Long id;
	private Dkp dkp;
	private Date date;
	private Mob from;
	private Integer wowHeadItemId;

	public Loot() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Dkp getDkp() {
		return dkp;
	}

	public void setDkp(Dkp dkp) {
		this.dkp = dkp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Mob getFrom() {
		return from;
	}

	public void setFrom(Mob from) {
		this.from = from;
	}

	public Integer getWowHeadItemId() {
		return wowHeadItemId;
	}

	public void setWowHeadItemId(Integer wowHeadItemId) {
		this.wowHeadItemId = wowHeadItemId;
	}

	@Override
	public String toString() {
		return "Loot [id=" + id + ", dkp=" + dkp + ", date=" + date + ", from=" + from
				+ ", wowHeadItemId=" + wowHeadItemId + "]";
	}
}
