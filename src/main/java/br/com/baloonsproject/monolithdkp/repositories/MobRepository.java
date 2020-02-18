package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baloonsproject.monolithdkp.api.entities.Mob;

public interface MobRepository extends JpaRepository<Mob, Long> {

}
