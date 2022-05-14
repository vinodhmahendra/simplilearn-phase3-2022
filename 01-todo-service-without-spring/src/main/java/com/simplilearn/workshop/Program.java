package com.simplilearn.workshop;

import com.simplilearn.workshop.service.TodoService;
import com.simplilearn.workshop.service.TodoServiceImpl;

public class Program {

	public static void main(String[] args) {

		TodoService theTodoService = new TodoServiceImpl();
		
		System.out.println(theTodoService.findAll().get(0).getDescription());

	}

}
