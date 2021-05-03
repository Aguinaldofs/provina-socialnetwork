package br.com.provina.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.provina.model.Item;

public class ItemDto {

	private String name;
	private LocalDateTime creationdate;
	private String url;

	public ItemDto(Item item) {
		this.name = item.getName();
		this.creationdate = item.getCreationdate();
		this.url = item.getUrl();
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

}
