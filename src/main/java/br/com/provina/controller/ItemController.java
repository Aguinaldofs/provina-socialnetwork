package br.com.provina.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.provina.controller.dto.ItemDetailDto;
import br.com.provina.controller.dto.ItemDto;
import br.com.provina.controller.form.ItemForm;
import br.com.provina.controller.form.ItemFormUpdate;
import br.com.provina.model.Item;
import br.com.provina.repository.CategoryRepository;
import br.com.provina.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping
	public List<ItemDto> list(String categoryName) {

		if (categoryName == null) {
			List<Item> items = itemRepository.findAll();
			return ItemDto.convert(items);
		} else {
			List<Item> items = itemRepository.findByCategoryName(categoryName);
			return ItemDto.convert(items);
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ItemDto> register(@RequestBody @Valid ItemForm form, UriComponentsBuilder uriBuilder) {
		Item item = form.convert(categoryRepository);
		itemRepository.save(item);

		URI uri = uriBuilder.path("/items/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemDto(item));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemDetailDto> detail(@PathVariable Long id) {

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ItemDetailDto(optional.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ItemDto> update(@PathVariable Long id, @RequestBody @Valid ItemFormUpdate form) {

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			Item item = form.update(id, itemRepository);
			return ResponseEntity.ok(new ItemDto(item));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			itemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}

}
