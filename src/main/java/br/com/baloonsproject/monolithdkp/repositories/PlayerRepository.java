package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.baloonsproject.monolithdkp.api.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

	@Transactional(readOnly = true)
	Player findByNickname(String nickname);
}
