package br.com.baloonsproject.monolithdkp.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Player;
import br.com.baloonsproject.monolithdkp.repositories.PlayerRepository;
import br.com.baloonsproject.monolithdkp.services.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);

	@Autowired
	private PlayerRepository repository;

	@Override
	public List<Player> findAllByNickname(List<String> nicknames) {
		LOGGER.info("Searching all players by nicknames of {}", nicknames);
		return repository.findAll(Sort.by(Sort.Direction.ASC, "nickname"));
	}

	@Override
	public Optional<Player> save(Player player) {
		LOGGER.info("Saving {}", player);
		return Optional.ofNullable(repository.save(player));
	}

	@Override
	public Optional<Player> findById(Long id) {
		LOGGER.info("Searching player with id {}", id);
		return repository.findById(id);
	}

}
