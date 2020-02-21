package br.com.baloonsproject.monolithdkp.services;

import java.util.List;
import java.util.Optional;

import br.com.baloonsproject.monolithdkp.api.entities.Player;

public interface PlayerService {
	/**
	 * @param nicknames
	 * @return
	 */
	List<Player> findAllByNickname(List<String> nicknames);

	/**
	 * @param player
	 * @return
	 */
	Optional<Player> save(Player player);

	/**
	 * @param id
	 * @return
	 */
	Optional<Player> findById(Long id);
}
