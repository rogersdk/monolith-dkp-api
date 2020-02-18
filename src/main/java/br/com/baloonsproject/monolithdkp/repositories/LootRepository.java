package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baloonsproject.monolithdkp.api.entities.Loot;

public interface LootRepository extends JpaRepository<Loot, Long> {

}
