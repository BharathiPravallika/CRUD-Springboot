package com.example.todoapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;

@Service
public class TodoService {
	private final TodoRepository todoRepository;
	
	@Autowired
	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	public List<Todo> findAllTodos(){
		return todoRepository.findAll();
	}
	
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public Optional<Todo> findTodoById(int id){
		return todoRepository.findById(id);
	}
	
	public Todo updateTodoStatus(int id, String status) {
		Todo todo = findTodoById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
		todo.setStatus(status);
		return saveTodo(todo);
	}
	
	public void deleteTodoById(int id) {
		todoRepository.deleteById(id);
	}
}
