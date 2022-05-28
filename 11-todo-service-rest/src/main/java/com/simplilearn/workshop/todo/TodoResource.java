package com.simplilearn.workshop.todo;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simplilearn.workshop.domain.Todo;
import com.simplilearn.workshop.errors.TodoNotFoundException;
import com.simplilearn.workshop.service.TodoService;

@RestController
//@Controller
public class TodoResource {

	@Autowired
	private TodoService todoService;

	// HTTP Method : GET
	// URI : /users/vinodh/todos/1
	@GetMapping(path = "/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username, @PathVariable long id) {
		
		Todo theTodo = todoService.findById(id);
		if ( theTodo == null ) {
			throw new TodoNotFoundException("id - " + id);
		}
		return theTodo;
	}

	// HTTP Method : GET
	// URI: /users/vinodh/todos
	@GetMapping(path = "/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return todoService.findAll();
	}

	// HTTP Method : DELETE
	// URI : /users/{username}/todos/{id}
	@DeleteMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> deleteTodo(@PathVariable String username, @PathVariable long id) {

		Todo theTodo = todoService.deleteById(id);

		if (theTodo != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	// HTTP Method : PUT
	// URI : /users/{username}/todos/{id}
	@PutMapping(path = "/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id,
			@RequestBody Todo theTodo) {
		Todo savedTodo = todoService.save(theTodo);

		return new ResponseEntity(theTodo, HttpStatus.OK);
	}

	// HTTP Method : POST
	// URI : /users/vinodh/todos
	// Request Body ---- > JSON Data { }
	@PostMapping(path = "/users/{username}/todos")
	public ResponseEntity createTodo(@PathVariable String username,@Valid @RequestBody Todo theTodo) {
		Todo savedTodo = todoService.save(theTodo);

		// Response Header : location
		// Status Code : 201
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTodo.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
