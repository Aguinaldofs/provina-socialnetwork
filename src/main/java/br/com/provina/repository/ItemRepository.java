package br.com.provina.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Page<Item> findByCategoryName(String categoryName, Pageable pagination);

}
