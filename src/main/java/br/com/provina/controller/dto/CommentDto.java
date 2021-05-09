package br.com.provina.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.provina.model.Comment;

public class CommentDto {

	private long id;
	private String message;
	private LocalDateTime creationDate;
	private UserDto user;

	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.message = comment.getText();
		this.creationDate = comment.getCreationdate();
		this.user = new UserDto(comment.getUser());
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

	public UserDto getUser() {
		return user;
	}

	public static List<CommentDto> convert(List<Comment> comments) {
		// TODO Auto-generated method stub
		return null;
	}

}
