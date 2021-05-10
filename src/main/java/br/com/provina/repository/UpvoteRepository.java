package br.com.provina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.Item;
import br.com.provina.model.Upvote;
import br.com.provina.model.User;

public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

	Optional<Upvote> findByUserAndItem(User user, Item item);

}
