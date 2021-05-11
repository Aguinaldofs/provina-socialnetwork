package br.com.provina.repository;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.provina.model.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
class CategoryRepositoryTest {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryRepositoryTest(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}

	@Test
	void ShouldReturnCategoryName() {
		String categoryName = "Engenharia da Computação";
		Category category = categoryRepository.findByName(categoryName);
		Assert.assertEquals(categoryName, category.getName());
	}

}
