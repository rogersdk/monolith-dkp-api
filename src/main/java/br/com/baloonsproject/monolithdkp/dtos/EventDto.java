package br.com.baloonsproject.monolithdkp.dtos;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

public class EventDto {

	private Long id;
	private String name;
	private String fileName;
	private Date date;

	@Transient
	private MultipartFile file;

	public EventDto() {
		// Default constructor
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "EventDto [id=" + id + ", name=" + name + ", fileName=" + fileName + ", date=" + date + "]";
	}

}
