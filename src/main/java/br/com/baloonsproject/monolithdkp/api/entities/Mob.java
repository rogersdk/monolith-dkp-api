package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;

public class Mob implements Serializable {

	private static final long serialVersionUID = 4311922618504231368L;

	private Long id;
	private String name;

	public Mob() {
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

	@Override
	public String toString() {
		return "Mob [id=" + id + ", name=" + name + "]";
	}

}
