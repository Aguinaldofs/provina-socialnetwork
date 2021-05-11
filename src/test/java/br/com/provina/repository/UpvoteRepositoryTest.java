package br.com.provina.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.provina.model.Item;
import br.com.provina.model.Upvote;
import br.com.provina.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UpvoteRepositoryTest {

	private ItemRepository itemRepository;
	private UserRepository userRepository;
	private UpvoteRepository upvoteRepository;

	@Autowired
	public UpvoteRepositoryTest(ItemRepository itemRepository, UserRepository userRepository,
			UpvoteRepository upvoteRepository) {
		super();
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
		this.upvoteRepository = upvoteRepository;
	}

	@Test
	void ShouldReturnUpvoteWithUserEmailAndItemCategoryName() {
		String email = "aguinaldojunior@gec.inatel.br";
		Optional<User> user = userRepository.findByEmail(email);
		Pageable pageable = null;
		String categoryName = "Engenharia da Computação";
		Page<Item> item = itemRepository.findByCategoryName(categoryName, pageable);
		Optional<Upvote> upvote = upvoteRepository.findByUserAndItem(user.get(), item.getContent().get(0));
		Assert.assertEquals(upvote.get().getUser().getEmail(), user.get().getEmail());
		Assert.assertEquals(upvote.get().getItem().getCategory().getName(),
				item.getContent().get(0).getCategory().getName());

	}
}
