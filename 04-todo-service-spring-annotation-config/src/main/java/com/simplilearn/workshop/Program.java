package com.simplilearn.workshop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.simplilearn.workshop.config.TodoServiceConfig;
import com.simplilearn.workshop.service.TodoService;

public class Program {

	public static void main(String[] args) {
		//spring container
//		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		 ApplicationContext context = new AnnotationConfigApplicationContext(TodoServiceConfig.class); //new code
		 
		TodoService theTodoService = context.getBean("todoService",TodoService.class);
		
		System.out.println(theTodoService.findAll().get(0).getDescription());

	}

}
