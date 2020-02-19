package br.com.baloonsproject.monolithdkp.services;

import java.util.Optional;

import br.com.baloonsproject.monolithdkp.api.entities.Event;

public interface EventService {

	/**
	 * Save an event
	 * 
	 * @param event
	 * @return
	 */
	Optional<Event> save(Event event);

	/**
	 * Search an event by it's file name
	 * 
	 * @param fileName
	 * @return
	 */
	Optional<Event> findByFileName(String fileName);

	/**
	 * @param id
	 * @return
	 */
	Optional<Event> findById(Long id);
}
