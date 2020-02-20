package br.com.baloonsproject.monolithdkp.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.api.entities.Mob;
import br.com.baloonsproject.monolithdkp.repositories.LootRepository;
import br.com.baloonsproject.monolithdkp.repositories.MobRepository;
import br.com.baloonsproject.monolithdkp.services.LootService;

@Service
public class LootServiceImpl implements LootService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LootServiceImpl.class);

	@Autowired
	private LootRepository repository;

	@Autowired
	private MobRepository mobRepository;

	@Override
	public Optional<Loot> save(Loot loot) {
		LOGGER.info("Saving {}", loot);
		return Optional.ofNullable(repository.save(loot));
	}

	@Override
	public Optional<Mob> findMobByName(String mobName) {
		LOGGER.info("Searching mob {}", mobName);
		return Optional.ofNullable(mobRepository.findByName(mobName));
	}

}
