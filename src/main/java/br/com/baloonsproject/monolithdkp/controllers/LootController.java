package br.com.baloonsproject.monolithdkp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.baloonsproject.monolithdkp.dtos.LootDto;
import br.com.baloonsproject.monolithdkp.response.Response;

@RestController
@RequestMapping("/api/loots")
@CrossOrigin(origins = "*")
public class LootController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LootController.class);
	
	public LootController() {
		// Default constructor
	}
	
	@GetMapping(value = "/findByEvent/{eventId}")
	public ResponseEntity<Response<LootDto>> findByEvent(@PathVariable("eventId") Long id) {
		LOGGER.info("{}", id);
		return null;
//		return ResponseEntity.ok(response);
	}
}
