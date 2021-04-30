package br.com.provina.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.provina.model.Category;
import br.com.provina.model.Item;
import br.com.provina.repository.CategoryRepository;

public class ItemForm {

	@NotNull
	@NotEmpty
	private String name;
	@NotNull
	@NotEmpty
	private String nameCategory;
	@NotNull
	@NotEmpty
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Item convert(CategoryRepository categoryRepository) {

		Category category = categoryRepository.findByName(nameCategory);
		return new Item(name, url, category);
	}

}
