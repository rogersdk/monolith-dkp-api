package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;
import java.util.Date;

public class Dkp implements Serializable {

	private static final long serialVersionUID = 7151343599121804478L;

	private Long id;
	private String description;
	private Date date;
	private Player player;
	private Event event;
	private Integer value;

	public Dkp() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Dkp [id=" + id + ", description=" + description + ", date=" + date + ", player=" + player + ", event="
				+ event + ", value=" + value + "]";
	}

}
