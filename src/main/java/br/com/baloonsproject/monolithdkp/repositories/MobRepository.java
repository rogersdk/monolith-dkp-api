package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.baloonsproject.monolithdkp.api.entities.Mob;

public interface MobRepository extends JpaRepository<Mob, Long> {

	@Transactional(readOnly = true)
	Mob findByName(String mobName);
}
