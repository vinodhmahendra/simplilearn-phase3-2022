package com.simplilearn.workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}
