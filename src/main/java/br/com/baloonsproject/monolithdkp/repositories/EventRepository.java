package br.com.baloonsproject.monolithdkp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.baloonsproject.monolithdkp.api.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	@Transactional(readOnly = true)
	Event findByFileName(String fileName);

	@Transactional(readOnly = true)
	Optional<Event> findByChecksum(String checksum);
}
