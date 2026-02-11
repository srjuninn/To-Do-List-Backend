package com.List.ToDo.service;

import java.util.List;
import java.util.Optional;

import com.List.ToDo.entities.UserEntity;
import com.List.ToDo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.List.ToDo.dto.TaskDTO;
import com.List.ToDo.entities.TaskEntity;
import com.List.ToDo.repositories.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TaskEntity createTask(Long userId, TaskDTO dto) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        TaskEntity task = new TaskEntity(dto);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<TaskEntity> getTasksByUser(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return user.getTasks();
    }

    public TaskEntity updateTask(Long taskId, TaskDTO dto) {
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setBeginDate(dto.getBeginDate());
        task.setEndDate(dto.getEndDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada");
        }
        taskRepository.deleteById(id);
    }

    public List<TaskEntity> showTasks() {
        return taskRepository.findAll();
    }

    public Optional<TaskEntity> showTasksById(Long id) {
        return taskRepository.findById(id);
    }
}
