package br.com.baloonsproject.monolithdkp.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.baloonsproject.monolithdkp.api.entities.Player;
import br.com.baloonsproject.monolithdkp.dtos.DkpDto;
import br.com.baloonsproject.monolithdkp.dtos.PlayerDto;
import br.com.baloonsproject.monolithdkp.response.Response;
import br.com.baloonsproject.monolithdkp.services.DkpService;
import br.com.baloonsproject.monolithdkp.services.PlayerService;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "*")
public class PlayerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private PlayerService playerService;

	@Autowired
	private DkpService dkpService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<PlayerDto>> findById(@PathVariable("id") Long id) {
		LOGGER.info("Searching for a Player with id {}", id);
		Response<PlayerDto> response = new Response<>();
		Optional<Player> player = playerService.findById(id);

		if (!player.isPresent()) {
			LOGGER.info("No player found");
			response.addErrorMessage(String.format("No player found with id %d", id));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(convertToDto(player.get()));
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/updated-dkp/{id}")
	public ResponseEntity<Response<DkpDto>> findPlayerUpdatedDkp(@PathVariable("id") Long id) {
		LOGGER.info("Get updated dkp value of the player {}", id);
		Response<DkpDto> response = new Response<>();
		Optional<Player> player = playerService.findById(id);

		Optional<DkpDto> dkp = dkpService.findUpdatedDkpByPlayerId(id);

		if (!dkp.isPresent()) {
			LOGGER.info("No value found");
			response.addErrorMessage(String.format("No value found with id %d", id));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(dkp.get());
		return ResponseEntity.ok(response);
	}

	private PlayerDto convertToDto(Player player) {
		PlayerDto dto = new PlayerDto();
		dto.setId(player.getId());
		dto.setNickname(player.getNickname());
		return dto;
	}
}
