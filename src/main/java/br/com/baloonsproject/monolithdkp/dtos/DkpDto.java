package br.com.baloonsproject.monolithdkp.dtos;

import java.util.Date;

public class DkpDto {

	private Long id;
	private String description;
	private Integer value;
	private Date date;
	private EventDto eventDto;
	private PlayerDto playerDto;

	public DkpDto() {
		// Default constructor
	}

	public DkpDto(Long value, Long playerId, String nickname) {
		this.value = value.intValue();
		this.playerDto = new PlayerDto(playerId, nickname);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EventDto getEventDto() {
		return eventDto;
	}

	public void setEventDto(EventDto eventDto) {
		this.eventDto = eventDto;
	}

	public PlayerDto getPlayerDto() {
		return playerDto;
	}

	public void setPlayerDto(PlayerDto playerDto) {
		this.playerDto = playerDto;
	}

	@Override
	public String toString() {
		return "DkpDto [id=" + id + ", description=" + description + ", value=" + value + ", date=" + date
				+ ", eventDto=" + eventDto + ", playerDto=" + playerDto + "]";
	}

}
