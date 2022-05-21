package com.simplilearn.workshop.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String helloWorld() {
		return  "Spring Boot 2! Hello World";
	}

}
