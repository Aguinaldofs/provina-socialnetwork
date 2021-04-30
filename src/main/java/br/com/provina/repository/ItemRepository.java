package br.com.provina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByCategoryName(String categoryName);

}
