package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;

import br.com.baloonsproject.monolithdkp.api.enums.ClassTypeEnum;

public class Player implements Serializable {

	private static final long serialVersionUID = -6318265376379693986L;

	private Long id;
	private String nickname;
	private ClassTypeEnum classType;

	public Player() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public ClassTypeEnum getClassType() {
		return classType;
	}

	public void setClassType(ClassTypeEnum classType) {
		this.classType = classType;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", nickname=" + nickname + ", classType=" + classType + "]";
	}

}
