package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.dtos.DkpDto;

public interface DkpRepository extends JpaRepository<Dkp, Long> {

	@Query("SELECT new br.com.baloonsproject.monolithdkp.dtos.DkpDto(SUM(d.value), p.id, p.nickname) "
			+ "FROM Dkp d JOIN d.player p WHERE p.id = :playerId GROUP BY p.id, p.nickname")
	@Transactional(readOnly = true)
	DkpDto findUpdatedDkpByPlayerId(Long playerId);
}
