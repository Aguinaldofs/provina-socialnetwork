package br.com.provina.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.provina.model.Item;

public class ItemDto {

	private Long id;
	private String name;
	private LocalDateTime creationdate;
	private String url;
	private int totalUpvotes;
	private int totalComments;
	private UserDto owner;

	public ItemDto(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.creationdate = item.getCreationdate();
		this.url = item.getUrl();
		this.totalUpvotes = item.getUpvotes().size();
		this.totalComments = item.getComments().size();
		this.owner = new UserDto(item.getOwner());
	}

	public String getName() {
		return name;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public String getUrl() {
		return url;
	}

	public static Page<ItemDto> convert(Page<Item> items) {

		return items.map(ItemDto::new);
	}

	public Long getId() {
		return id;
	}

	public int getTotalUpvotes() {
		return totalUpvotes;
	}

	public int getTotalComments() {
		return totalComments;
	}

	public UserDto getOwner() {
		return owner;
	}

}
