package br.com.provina.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.provina.model.Item;

public class ItemDetailDto {

	private Long id;
	private String name;
	private String message;
	private LocalDateTime creationdate = LocalDateTime.now();
	private String url;
	private List<CommentDto> comments;

	public ItemDetailDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.creationdate = item.getCreationdate();
		this.url = item.getUrl();
		this.comments = new ArrayList<>();
		this.comments.addAll(item.getComments().stream().map(CommentDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public String getUrl() {
		return url;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

}
