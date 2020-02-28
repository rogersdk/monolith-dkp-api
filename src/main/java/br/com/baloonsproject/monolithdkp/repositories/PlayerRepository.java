package br.com.baloonsproject.monolithdkp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.baloonsproject.monolithdkp.api.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Transactional(readOnly = true)
	@Query("SELECT p FROM Player p WHERE p.nickname IN (:nicknames) ORDER BY p.nickname ASC")
	List<Player> findAllByNickname(List<String> nicknames);
}
