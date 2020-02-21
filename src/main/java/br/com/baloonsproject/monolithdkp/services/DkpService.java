package br.com.baloonsproject.monolithdkp.services;

import java.util.Optional;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.dtos.DkpDto;

public interface DkpService {

	/**
	 * @param dkp
	 * @return
	 */
	Optional<Dkp> save(Dkp dkp);

	/**
	 * @param playerId
	 * @return
	 */
	Optional<DkpDto> findUpdatedDkpByPlayerId(Long playerId);
}
