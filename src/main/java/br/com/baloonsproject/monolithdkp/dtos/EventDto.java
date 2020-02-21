package br.com.baloonsproject.monolithdkp.dtos;

import java.util.Date;

public class EventDto {

	private Long id;
	private String name;
	private String fileName;
	private Date date;

	public EventDto() {
		// Default constructor
	}

	public EventDto(Long eventId) {
		this.id = eventId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "EventDto [id=" + id + ", name=" + name + ", fileName=" + fileName + ", date=" + date + "]";
	}

}
