package com.List.ToDo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.List.ToDo.dto.TaskDTO;
import com.List.ToDo.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("task")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

//	Creating task 
	@PostMapping("/create")
	public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO dto) {
		taskService.createTask(dto);
		return ResponseEntity.ok("Tarefa criada com sucesso");
	}

//	Deleting task 
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id) {
		String result = taskService.deleteTask(id);
		return ResponseEntity.ok(result);

	}
}
