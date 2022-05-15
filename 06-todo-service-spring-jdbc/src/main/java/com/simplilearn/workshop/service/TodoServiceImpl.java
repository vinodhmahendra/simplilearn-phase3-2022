package com.simplilearn.workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.workshop.domain.Todo;
import com.simplilearn.workshop.repository.TodoRepository;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService{
	
	//depends on 'TodoRepository' 
//	private TodoRepository todoRepository = new TodoRepositoryImpl(); //old code
	
	@Autowired
	private TodoRepository todoRepository; //property name
	
	public int getTodosCount() {
		return todoRepository.getTodosCount();
	}
	
	
	//spring default constructor
	public TodoServiceImpl() {
		
	}
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		super();
		System.out.println("spring container called constructor to  assemble 'todoRepository' bean");
		this.todoRepository = todoRepository;
	}

	public void setTodoRepository(TodoRepository todoRepository) {
		System.out.println("spring container called setter method to  assemble 'todoRepository' bean");
		this.todoRepository = todoRepository;
	}

	@Override
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	@Override
	public Todo save(Todo theTodo) {
		return todoRepository.save(theTodo);
	}

	@Override
	public Todo deleteById(long theId) {
		return todoRepository.deleteById(theId);
	}

	@Override
	public Todo findById(long theId) {
		return todoRepository.findById(theId);
	}

}
