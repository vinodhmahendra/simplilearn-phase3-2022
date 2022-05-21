package com.simplilearn.workshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.simplilearn.workshop.domain.Todo;
import com.simplilearn.workshop.events.TodosEvent;
import com.simplilearn.workshop.repository.TodoRepository;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService,ApplicationEventPublisherAware{
	
	//depends on 'TodoRepository' 
//	private TodoRepository todoRepository = new TodoRepositoryImpl(); //old code
	
	@Autowired
	private TodoRepository todoRepository; //property name
	
	public int getTodosCount() {
		return todoRepository.getTodosCount();
	}
	
	
	//spring default constructor
	public TodoServiceImpl() {
		
	}
	
	public TodoServiceImpl(TodoRepository todoRepository) {
		super();
		System.out.println("spring container called constructor to  assemble 'todoRepository' bean");
		this.todoRepository = todoRepository;
	}

	public void setTodoRepository(TodoRepository todoRepository) {
		System.out.println("spring container called setter method to  assemble 'todoRepository' bean");
		this.todoRepository = todoRepository;
	}

	@Override
	public List<Todo> findAll() {
		publisher.publishEvent(new TodosEvent(this, "FINDALL", todoRepository));
		return todoRepository.findAll();
	}

	@Override
	public Todo save(Todo theTodo) {
		publisher.publishEvent(new TodosEvent(this, "SAVE", todoRepository));
		return todoRepository.save(theTodo);
	}

	@Override
	public Todo deleteById(long theId) {
		publisher.publishEvent(new TodosEvent(this, "DELETEBYID", todoRepository));
		return todoRepository.deleteById(theId);
	}

	@Override
	public Todo findById(long theId) {
		publisher.publishEvent(new TodosEvent(this, "FINDABYID", todoRepository));
		return todoRepository.findById(theId);
	}


	private  ApplicationEventPublisher publisher; 
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
		
	}

}
