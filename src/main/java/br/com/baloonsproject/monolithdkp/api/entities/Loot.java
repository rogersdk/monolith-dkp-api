package br.com.baloonsproject.monolithdkp.api.entities;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

public class Loot implements Serializable {

	private static final long serialVersionUID = -7595346736100979483L;

	private Long id;
	private Player player;
	private Item item;
	private Dkp dkp;
	private Date date;

	public Loot() {
		// Default constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	@Override
	public String toString() {
		return "Loot [id=" + id + ", player=" + player + ", item=" + item + ", dkp=" + dkp + ", data=" + date + "]";
	}

}
