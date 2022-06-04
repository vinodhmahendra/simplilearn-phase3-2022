package com.simplilearn.workshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.workshop.domain.Todo;

@Controller
public class TodoController {
	
	private static final String BASE_URL ="http://localhost:8080/users/vinodh/todos";
	
	
	private RestTemplate restTemplate;
	
	@Autowired
	public TodoController(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	@GetMapping(path="/users/todos")
	public ModelAndView listOfTodos() {
		
		ResponseEntity<List<Todo>> responseEntity =
				restTemplate.exchange(BASE_URL, HttpMethod.GET,null,new ParameterizedTypeReference<List<Todo>>() {
				});
		
		List<Todo> todos = responseEntity.getBody();
		
		ModelAndView modelAndView = new ModelAndView("list-todos");
		
		modelAndView.addObject("todos", todos);
		
		return modelAndView;
	}
	
	@GetMapping(path = "/users/todos/showFormForAdd")
	public ModelAndView showFormForAddTodos() {
		
		ModelAndView modelAndView = new ModelAndView("todos-form");
		
		Todo theTodo = new Todo();
		
		modelAndView.addObject("todos", theTodo);
		
		return modelAndView;
	}
	
	@PostMapping(path = "/users/todos/saveTodos")
	public ModelAndView saveTodo(@ModelAttribute("todos") Todo theTodo) {
		ModelAndView modelAndView = new ModelAndView("redirect:/users/todos");
		theTodo.setUsername("vinodh"); // bad code
		restTemplate.postForObject(BASE_URL, theTodo, Todo.class);
		

		return modelAndView;
	}
	
	@GetMapping(path = "/users/todos/showFormForUpdate")
	public ModelAndView showFormForUpdateTodos(@RequestParam("todoId") Long theId,Model theModel) {
		
		Map<String, Long> params = new HashMap<>();
		params.put("theId", theId);
		
		Todo theTodo = restTemplate.getForObject(BASE_URL + "/{theId}", Todo.class, params);
		theModel.addAttribute("todos", theTodo);
		return new ModelAndView("todos-form");
		
	}
	
	@GetMapping(path = "/users/todos/delete")
	public ModelAndView deleteTodos(@RequestParam("todoId") Long theId) {
		Map<String, Long> params = new HashMap<>();
		params.put("theId", theId);
		
		restTemplate.delete(BASE_URL+"/{theId}",params);
		
		
		ResponseEntity<List<Todo>> responseEntity =
				restTemplate.exchange(BASE_URL, HttpMethod.GET,null,new ParameterizedTypeReference<List<Todo>>() {
				});
		
		List<Todo> todos = responseEntity.getBody();
		
		ModelAndView modelAndView = new ModelAndView("list-todos");
		
		modelAndView.addObject("todos", todos);
		
		return modelAndView;
		
	}
}
