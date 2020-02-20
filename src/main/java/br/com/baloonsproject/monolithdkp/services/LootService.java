package br.com.baloonsproject.monolithdkp.services;

import java.util.Optional;

import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.api.entities.Mob;

public interface LootService {
	/**
	 * @param loot
	 * @return
	 */
	Optional<Loot> save(Loot loot);

	/**
	 * @param mobName
	 * @return
	 */
	Optional<Mob> findMobByName(String mobName);
}
