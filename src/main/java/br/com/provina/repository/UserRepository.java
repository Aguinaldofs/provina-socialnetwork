package br.com.provina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findById(String userName);
}