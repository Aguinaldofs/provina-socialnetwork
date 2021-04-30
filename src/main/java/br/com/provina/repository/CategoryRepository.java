package br.com.provina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String nameCategory);

}
