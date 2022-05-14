package com.simplilearn.workshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplilearn.workshop.repository.TodoRepository;
import com.simplilearn.workshop.repository.TodoRepositoryImpl;
import com.simplilearn.workshop.service.TodoService;
import com.simplilearn.workshop.service.TodoServiceImpl;

@Configuration
public class TodoServiceConfig {

	// bean methods
	@Bean
	public TodoRepository todoRepository() {
		TodoRepositoryImpl todoRepository = new TodoRepositoryImpl();
		return todoRepository;
	}

	@Bean
	public TodoService todoService() {
		TodoServiceImpl todoService = new TodoServiceImpl(todoRepository());
//		todoService.setTodoRepository();
		return todoService;
	}
}
