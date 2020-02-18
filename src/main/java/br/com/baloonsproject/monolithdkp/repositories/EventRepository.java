package br.com.baloonsproject.monolithdkp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.baloonsproject.monolithdkp.api.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}
