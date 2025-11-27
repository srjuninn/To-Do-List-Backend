package com.List.ToDo.service;

import org.springframework.stereotype.Service;

import com.List.ToDo.dto.TaskDTO;
import com.List.ToDo.entities.TaskEntity;
import com.List.ToDo.repositories.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public void createTask(TaskDTO dto) {

		TaskEntity task = new TaskEntity(dto);
		taskRepository.save(task);
	}
}
