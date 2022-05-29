package com.simplilearn.workshop.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//HTTP Method : GET
	//URI : /hello-world
	@RequestMapping(path = "/hello-world",method = RequestMethod.GET)
	public String helloWorld() {
		return "Hello World";
	}
	
	//HTTP Method : GET
	//URI: /hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World  -Changed");
	}
	
	//HTTP Method : GET
	//URI : /hello-world/path-variable/vinodh
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

}
