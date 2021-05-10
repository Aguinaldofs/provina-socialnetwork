package br.com.provina.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.provina.config.cloudinary.CloudinaryService;
import br.com.provina.controller.dto.CommentDto;
import br.com.provina.controller.dto.ItemDetailDto;
import br.com.provina.controller.dto.ItemDto;
import br.com.provina.model.Category;
import br.com.provina.model.Comment;
import br.com.provina.model.Item;
import br.com.provina.model.Upvote;
import br.com.provina.model.User;
import br.com.provina.model.VotingStatus;
import br.com.provina.repository.CategoryRepository;
import br.com.provina.repository.CommentRepository;
import br.com.provina.repository.ItemRepository;
import br.com.provina.repository.UpvoteRepository;
import br.com.provina.repository.UserRepository;

@RestController
@RequestMapping("/items")
public class ItemController {

	private ItemRepository itemRepository;

	private CategoryRepository categoryRepository;

	private CloudinaryService cloudinaryService;

	private UserRepository userRepository;

	private CommentRepository commentRepository;

	private UpvoteRepository upvoteRepository;

	@Autowired
	public ItemController(ItemRepository itemRepository, CategoryRepository categoryRepository,
			CloudinaryService cloudinaryService, UserRepository userRepository, CommentRepository commentRepository,
			UpvoteRepository upvoteRepository) {
		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.cloudinaryService = cloudinaryService;
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
		this.upvoteRepository = upvoteRepository;

	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "itemsList", allEntries = true)
	public ResponseEntity<ItemDto> addItem(Authentication authentication, @RequestParam("name") String name,
			@RequestParam("nameCategory") String nameCategory, @RequestParam("file") MultipartFile file,
			UriComponentsBuilder uriBuilder) throws IOException {

		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());
		Category category = categoryRepository.findByName(nameCategory);

		if (file != null) {
			Map uploadResult = cloudinaryService.upload(file, "images");
			String media = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
		}

		Item item = new Item(name, file.getOriginalFilename(), category, user);
		itemRepository.save(item);

		URI uri = uriBuilder.path("/items/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemDto(item));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	@Cacheable(value = "itemsList")
	public Page<ItemDto> listItem(@RequestParam(required = false) String categoryName,
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
	public ResponseEntity<ItemDetailDto> detailItem(@PathVariable Long id) {

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ItemDetailDto(optional.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteItem(@PathVariable Long id) {

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			itemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();

	}

	@PostMapping("/{id}/comments")
	@Transactional
	public ResponseEntity<?> addComment(Authentication authentication, @RequestParam("text") String text,
			@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws IOException {

		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());

		@SuppressWarnings("rawtypes")
		Map uploadResult = cloudinaryService.upload(file, "images");

		String media = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();

		Optional<Item> optional = itemRepository.findById(id);
		if (optional.isPresent()) {
			Comment comment = new Comment(text, file.getOriginalFilename(), optional.get(), user);
			commentRepository.save(comment);
			return ResponseEntity.created(null).build();
		}

		return ResponseEntity.notFound().build();

	}

	@GetMapping("/{id}/comments")
	public ResponseEntity<?> listComment(@PathVariable Long id) {

		Optional<Item> optional = itemRepository.findById(id);
		Optional<Comment> comment = commentRepository.findById(id);
		if (optional.isPresent()) {
			List<Comment> comments = optional.get().getComments();
			List<CommentDto> commentsDto = CommentDto.convert(comments);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}/comments/{id}")
	@Transactional
	public ResponseEntity<?> deleteComment(Authentication authentication, @PathVariable("id") Long id) {
		Optional<Comment> optional = commentRepository.findById(id);
		if (optional.isPresent()) {
			commentRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping("/{id}/upvotes")
	@Transactional
	public ResponseEntity<?> addUpvotes(Authentication authentication, @PathVariable("id") Long id,
			@RequestParam("status") VotingStatus status) {

		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());

		Optional<Item> optionalItem = itemRepository.findById(id);
		if (optionalItem.isPresent()) {
			Optional<Upvote> upvoteExists = upvoteRepository.findByUserAndItem(user, optionalItem.get());
			if (upvoteExists.isPresent()) {
				return ResponseEntity.status(403).build();
			}

			Upvote upvote = new Upvote(authenticatedUser, optionalItem.get(), status);
			upvoteRepository.save(upvote);

			return ResponseEntity.created(null).build();

		}
		return ResponseEntity.notFound().build();

	}

}
