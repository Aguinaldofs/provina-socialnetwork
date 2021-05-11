package br.com.provina.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.provina.model.Item;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTest {

	private ItemRepository itemRepository;

	@Autowired
	public ItemRepositoryTest(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Test
	void shouldReturnCategoryName() {
		Pageable pageable = null;
		String categoryName = "Engenharia da Computação";
		Page<Item> item = itemRepository.findByCategoryName(categoryName, pageable);
		Assert.assertEquals(categoryName, item.getContent().get(0).getCategory().getName());
	}

}
