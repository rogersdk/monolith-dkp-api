package br.com.baloonsproject.monolithdkp.services;

import java.util.List;

import br.com.baloonsproject.monolithdkp.api.entities.Player;

public interface PlayerService {
	/**
	 * @param name
	 * @return
	 */
	List<Player> findAllByName(List<String> name);
}
