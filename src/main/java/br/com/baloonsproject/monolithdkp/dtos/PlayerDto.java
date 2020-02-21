package br.com.baloonsproject.monolithdkp.dtos;

public class PlayerDto {

	private Long id;
	private String nickname;

	public PlayerDto() {
		// Default constructor
	}

	public PlayerDto(Long playerId, String nickname) {
		this.id = playerId;
		this.nickname = nickname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "PlayerDto [id=" + id + ", nickname=" + nickname + "]";
	}

}
