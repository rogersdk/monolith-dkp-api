package br.com.baloonsproject.monolithdkp.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.dtos.EventDto;
import br.com.baloonsproject.monolithdkp.response.Response;
import br.com.baloonsproject.monolithdkp.services.EventService;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService service;

	public EventController() {
		// Default Constructor
	}

	/**
	 * @param fileName
	 * @return
	 */
	@GetMapping(value = "/fileName/{fileName}")
	public ResponseEntity<Response<EventDto>> findByFileName(@PathVariable("fileName") String fileName) {
		LOGGER.info("");
		Response<EventDto> response = new Response<>();
		Optional<Event> event = service.findByFileName(fileName);

		if (!event.isPresent()) {
			LOGGER.info("");
			response.getErrors().add("MESSAGE OF NOT FOUND");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(convertEventDto(event.get()));
		return ResponseEntity.ok(response);
	}

	private EventDto convertEventDto(Event event) {
		EventDto dto = new EventDto();
		return dto;
	}

	@PostMapping(value = "/")
	public ResponseEntity<Response<EventDto>> registerEvent(@Valid @RequestBody EventDto eventDto,
			BindingResult result) throws UnsupportedEncodingException, IOException {
		LOGGER.info("");

		Event eventToBeSaved = convertDtoToEvent(eventDto);

//		Optional<Event> event = service.save(newEvent);

		Response<EventDto> response = new Response<>();

		response.setData(eventDto);

		return ResponseEntity.ok(response);
	}

	private Event convertDtoToEvent(@Valid EventDto eventDto) throws UnsupportedEncodingException, IOException {
		Event event = new Event();
		event.setFileContent(new String(eventDto.getFile().getBytes(), "UTF-8"));

		return event;
	}

}
