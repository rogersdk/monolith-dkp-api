package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.baloonsproject.monolithdkp.api.enums.ClassTypeEnum;

@Entity
@Table(name = "player")
public class Player implements Serializable {

	private static final long serialVersionUID = -6318265376379693986L;

	private Long id;
	private String nickname;
	private ClassTypeEnum classType;

	public Player() {
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

	@Column(name = "nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "class_type")
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
