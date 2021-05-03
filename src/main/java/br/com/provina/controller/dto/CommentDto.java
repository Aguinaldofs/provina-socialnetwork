package br.com.provina.controller.dto;

import java.time.LocalDateTime;

import br.com.provina.model.Comment;

public class CommentDto {

	private long id;
	private String message;
	private LocalDateTime creationDate;
	private String username;

	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.message = comment.getText();
		this.creationDate = comment.getCreationdate();
		this.username = comment.getUser().getName();
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getUsername() {
		return username;
	}

}
