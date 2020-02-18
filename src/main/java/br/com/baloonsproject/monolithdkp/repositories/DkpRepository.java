package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;

public interface DkpRepository extends JpaRepository<Dkp, Long> {

}
