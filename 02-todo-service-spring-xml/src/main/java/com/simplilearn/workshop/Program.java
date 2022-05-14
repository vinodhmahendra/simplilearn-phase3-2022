package com.simplilearn.workshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simplilearn.workshop.service.TodoService;

public class Program {

	public static void main(String[] args) {
		//spring container
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		TodoService theTodoService = context.getBean("todoService",TodoService.class);
		
		System.out.println(theTodoService.findAll().get(0).getDescription());

	}

}
