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
	private int totalUpvotes;
	private LocalDateTime creationdate = LocalDateTime.now();

	public List<UpvoteDto> getUpvotes() {
		return upvotes;
	}

	private String url;
	private List<CommentDto> comments;
	private List<UpvoteDto> upvotes;

	public ItemDetailDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.creationdate = item.getCreationdate();
		this.url = item.getUrl();
		this.comments = new ArrayList<>();
		this.comments.addAll(item.getComments().stream().map(CommentDto::new).collect(Collectors.toList()));
		this.upvotes = new ArrayList<>();
		this.upvotes.addAll(item.getUpvotes().stream().map(UpvoteDto::new).collect(Collectors.toList()));
		this.totalUpvotes = this.upvotes.size();
	}

	public int getTotalUpvotes() {
		return totalUpvotes;
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
