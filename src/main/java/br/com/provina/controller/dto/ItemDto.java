package br.com.provina.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

	public static List<ItemDto> convert(List<Item> items) {

		return items.stream().map(ItemDto::new).collect(Collectors.toList());
	}

}
