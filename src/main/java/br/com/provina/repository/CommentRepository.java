package br.com.provina.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.provina.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
