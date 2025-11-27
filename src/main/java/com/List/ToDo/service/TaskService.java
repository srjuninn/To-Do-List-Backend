package com.List.ToDo.service;

import java.util.List;
import java.util.Optional;

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

	public String deleteTask(Long id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return "tarefa excluida com sucesso!";
		} else {
			return "Essa tarefa não existe";
		}
	}

	public List<TaskEntity> showTasks() {
		return taskRepository.findAll();
	}

	public Optional<TaskEntity> showTasksById(Long id) {
		return taskRepository.findById(id);
	}
}
