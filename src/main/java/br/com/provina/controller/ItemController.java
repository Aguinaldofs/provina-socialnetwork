package br.com.provina.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.provina.config.cloudinary.CloudinaryService;
import br.com.provina.controller.dto.ItemDetailDto;
import br.com.provina.controller.dto.ItemDto;
import br.com.provina.controller.form.CommentForm;
import br.com.provina.controller.form.ItemFormUpdate;
import br.com.provina.model.Category;
import br.com.provina.model.Comment;
import br.com.provina.model.Item;
import br.com.provina.model.User;
import br.com.provina.repository.CategoryRepository;
import br.com.provina.repository.CommentRepository;
import br.com.provina.repository.ItemRepository;
import br.com.provina.repository.UserRepository;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CloudinaryService cloudinaryService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@PostMapping
	@Transactional
	@CacheEvict(value = "itemsList", allEntries = true)
	public ResponseEntity<ItemDto> register(Authentication authentication, @RequestParam("name") String name,
			@RequestParam("nameCategory") String nameCategory, @RequestParam("file") MultipartFile file,
			UriComponentsBuilder uriBuilder) throws IOException {

		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());
		Category category = categoryRepository.findByName(nameCategory);

		@SuppressWarnings("rawtypes")
		Map uploadResult = cloudinaryService.upload(file, "images");

		@SuppressWarnings("unused")
		String media = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
		Item item = new Item(name, file.getOriginalFilename(), category, user);
		itemRepository.save(item);

		URI uri = uriBuilder.path("/items/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemDto(item));

	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	@Cacheable(value = "itemsList")
	public Page<ItemDto> list(@RequestParam(required = false) String categoryName,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pagination) {

		if (categoryName == null) {
			Page<Item> items = itemRepository.findAll(pagination);
			return ItemDto.convert(items);
		} else {
			Page<Item> items = itemRepository.findByCategoryName(categoryName, pagination);
			return ItemDto.convert(items);
		}

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

	@PostMapping("/{id}/comments")
	@Transactional
	public ResponseEntity<?> addComment(Authentication authentication, @RequestBody @Valid CommentForm form,
			@PathVariable("id") Long id) {

		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());
		Optional<Item> item = itemRepository.findById(id);
		if (item.isPresent()) {
			Comment comment = form.convert(user, item.get());
			commentRepository.save(comment);
			return ResponseEntity.created(null).build();
		}

		return ResponseEntity.notFound().build();

	}

}
