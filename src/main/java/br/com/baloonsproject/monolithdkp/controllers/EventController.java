package br.com.baloonsproject.monolithdkp.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.baloonsproject.monolithdkp.api.entities.Dkp;
import br.com.baloonsproject.monolithdkp.api.entities.Event;
import br.com.baloonsproject.monolithdkp.api.entities.Loot;
import br.com.baloonsproject.monolithdkp.api.entities.Mob;
import br.com.baloonsproject.monolithdkp.api.entities.Player;
import br.com.baloonsproject.monolithdkp.dtos.EventDto;
import br.com.baloonsproject.monolithdkp.response.Response;
import br.com.baloonsproject.monolithdkp.services.EventService;
import br.com.baloonsproject.monolithdkp.services.LootService;
import br.com.baloonsproject.monolithdkp.services.MonolithDkpParser;
import br.com.baloonsproject.monolithdkp.services.PlayerService;
import br.com.baloonsproject.monolithdkp.utils.DateUtils;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private MonolithDkpParser parserService;

	@Autowired
	private EventService eventService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private LootService lootService;

	public EventController() {
		// Default Constructor
	}

	/**
	 * @param fileName
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<EventDto>> findByFileName(@PathVariable("id") Long id) {
		LOGGER.info("Searching for an Event with id {}", id);
		Response<EventDto> response = new Response<>();
		Optional<Event> event = eventService.findById(id);

		if (!event.isPresent()) {
			LOGGER.info("No event found");
			response.getErrors().add(String.format("No event found with id %d", id));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(convertEventDto(event.get()));
		return ResponseEntity.ok(response);
	}

	private EventDto convertEventDto(Event event) {
		EventDto dto = new EventDto();
		dto.setDate(event.getDate());
		dto.setFileName(event.getFileName());
		dto.setId(event.getId());
		dto.setName(event.getName());
		return dto;
	}

	@PostMapping(value = "")
	public ResponseEntity<Response<EventDto>> registerEvent(@RequestParam MultipartFile file,
			@RequestParam String eventName) throws IOException {
		LOGGER.info("Event register start...");
		Response<EventDto> response = new Response<>();

		Path filepath = Paths.get("/tmp", file.getOriginalFilename());
		File loadedFile = filepath.toFile();
		
		
		Event event = new Event();
		event.setName(eventName);
		event.setFileName(loadedFile.getName());
		event.setDate(DateUtils.getDateFromFileName(loadedFile.getName()));

		List<Player> players = parsePlayers(loadedFile);

		event.setPlayers(players);

		Optional<Event> newEvent = eventService.save(event);

		List<Loot> loots = parserService.parseLootHistory(loadedFile);

		for (Loot l : loots) {
			Dkp dkp = l.getDkp();
			dkp.setEvent(event);
			for (Player p : players) {
				if (dkp.getPlayer().getNickname().equals(p.getNickname())) {
					dkp.setPlayer(p);
				}
			}

			Optional<Mob> m = lootService.findMobByName(l.getFrom().getName());
			if (m.isPresent()) {
				l.setFrom(m.get());
			}

			lootService.save(l);
		}

		if (!newEvent.isPresent()) {
			response.addErrorMessage("Event not created");
			return ResponseEntity.badRequest().body(response);
		}

		EventDto responseDto = convertEventDto(newEvent.get());
		response.setData(responseDto);

		LOGGER.info("End of registering the new event {}", newEvent.get());
		
		return ResponseEntity.ok(response);

	}

	private List<Player> parsePlayers(File loadedFile) throws IOException {
		List<Player> parsedPlayers = parserService.parsePlayers(loadedFile);

		List<Player> players = playerService
				.findAllByNickname(parsedPlayers.stream().map(Player::getNickname).collect(Collectors.toList()));

		List<String> existingPlayersNicknames = players.stream().map(Player::getNickname).collect(Collectors.toList());

		for (Player p : parsedPlayers) {
			if (!existingPlayersNicknames.contains(p.getNickname())) {
				LOGGER.info("New player found {}", p);
				players.add(p);
			}
		}
		return players;
	}

}
