package br.com.baloonsproject.monolithdkp.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.dtos.DkpDto;
import br.com.baloonsproject.monolithdkp.repositories.DkpRepository;
import br.com.baloonsproject.monolithdkp.services.DkpService;

@Service
public class DkpServiceImpl implements DkpService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DkpServiceImpl.class);

	@Autowired
	private DkpRepository repository;

	@Override
	public Optional<Dkp> save(Dkp dkp) {
		LOGGER.info("Saving a new dkp {}", dkp);
		return Optional.ofNullable(repository.save(dkp));
	}

	@Override
	public Optional<DkpDto> findUpdatedDkpByPlayerId(Long playerId) {
		LOGGER.info("Searching for the updated player dkp of player {}", playerId);
		return Optional.ofNullable(repository.findUpdatedDkpByPlayerId(playerId));
	}

}
