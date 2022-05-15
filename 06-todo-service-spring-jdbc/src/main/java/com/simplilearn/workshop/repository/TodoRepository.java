package com.simplilearn.workshop.repository;

import java.util.List;

import com.simplilearn.workshop.domain.Todo;

public interface TodoRepository {
	public List<Todo> findAll();
	public Todo save(Todo theTodo);
	public Todo deleteById(long theId);
	public Todo findById(long theId);
	public int getTodosCount();
}
