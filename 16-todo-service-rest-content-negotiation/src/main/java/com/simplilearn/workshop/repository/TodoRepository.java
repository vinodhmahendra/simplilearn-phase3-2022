package com.simplilearn.workshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.workshop.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByUsername(String username);
	

}
