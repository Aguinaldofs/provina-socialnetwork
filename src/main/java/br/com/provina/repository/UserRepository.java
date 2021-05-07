package br.com.provina.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
}
