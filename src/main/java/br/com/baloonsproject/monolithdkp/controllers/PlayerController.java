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
import br.com.baloonsproject.monolithdkp.dtos.PlayerDto;
import br.com.baloonsproject.monolithdkp.response.Response;
import br.com.baloonsproject.monolithdkp.services.PlayerService;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "*")
public class PlayerController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private PlayerService playerService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<PlayerDto>> findById(@PathVariable("id") Long id) {
		LOGGER.info("Searching for a Player with id {}", id);
		Response<PlayerDto> response = new Response<>();
		Optional<Player> player = playerService.findById(id);

		if (!player.isPresent()) {
			LOGGER.info("No player found");
			response.addErrorMessage(String.format("No p layer found with id %d", id));
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(convertToDto(player.get()));
		return ResponseEntity.ok(response);
	}

	private PlayerDto convertToDto(Player player) {
		PlayerDto dto = new PlayerDto();
		dto.setId(player.getId());
		dto.setNickname(player.getNickname());
		return dto;
	}
}
