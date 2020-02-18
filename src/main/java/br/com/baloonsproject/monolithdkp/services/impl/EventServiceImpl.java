package br.com.baloonsproject.monolithdkp.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.repositories.EventRepository;
import br.com.baloonsproject.monolithdkp.services.EventService;

@Service
public class EventServiceImpl implements EventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	private EventRepository repository;

	@Override
	public Optional<Event> save(Event event) {
		LOGGER.info("Saving {}", event);
		return Optional.ofNullable(repository.save(event));
	}

	@Override
	public Optional<Event> findByFileName(String fileName) {
		LOGGER.info("Searching for an event by the fileName of {}", fileName);
		return Optional.ofNullable(repository.findByFileName(fileName));
	}

}
