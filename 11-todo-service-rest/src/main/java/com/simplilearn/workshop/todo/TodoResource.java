package com.simplilearn.workshop.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.workshop.domain.Todo;
import com.simplilearn.workshop.service.TodoService;

@RestController
public class TodoResource {

	@Autowired
	private TodoService todoService;
	
	//HTTP Method : GET
	//URI : /users/vinodh/todos/1
	@GetMapping(path="/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,@PathVariable long id) {
		return todoService.findById(id);
	}

	//HTTP Method : GET
	//URI: /users/vinodh/todos
	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}

}
