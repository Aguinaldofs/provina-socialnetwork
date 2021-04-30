package br.com.provina.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.provina.model.Item;
import br.com.provina.repository.ItemRepository;

public class ItemFormUpdate {

	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Item update(Long id, ItemRepository itemRepository) {
		Item item = itemRepository.getOne(id);
		item.setName(this.name);
		item.setUrl(this.url);
		return item;
	}

}
