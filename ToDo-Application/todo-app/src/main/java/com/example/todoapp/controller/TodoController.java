package com.example.todoapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import com.example.todoapp.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	private final TodoService todoService;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping
	public List <Todo> getAllTodos(){
		return todoService.findAllTodos();
	}
	
	@PostMapping
	public Todo createTodo(@RequestBody Todo todo) {
		return todoService.saveTodo(todo);
	}
	
	@GetMapping("/{id}")
	public Todo getTodoById(@PathVariable int id) {
		return todoService.findTodoById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));
	}
	
	@PutMapping("/{id}")
	public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo) {
		Todo existingTodo = todoService.findTodoById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));
		existingTodo.setStatus(todo.getStatus());
		return todoService.saveTodo(existingTodo);
	}
	
	@DeleteMapping("/{id}")
		public void deleteTodo(@PathVariable int id) {
			todoService.findTodoById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found"));
			todoService.deleteTodoById(id);
			
		}
}
