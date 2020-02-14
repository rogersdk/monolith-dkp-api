package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {

	private static final long serialVersionUID = 3194653732060234594L;

	private Long id;
	private String name;
	private Date date;
	private List<Player> players = new ArrayList<>();

	public Event() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", data=" + date + ", players=" + players + "]";
	}

}
