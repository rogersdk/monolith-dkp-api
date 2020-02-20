package br.com.baloonsproject.monolithdkp.services;

import java.util.Optional;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;

public interface DkpService {

	/**
	 * @param dkp
	 * @return
	 */
	Optional<Dkp> save(Dkp dkp);
}
