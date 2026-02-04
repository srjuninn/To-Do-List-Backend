package com.List.ToDo.controller;

import java.util.List;
import java.util.Optional;

import com.List.ToDo.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.List.ToDo.dto.TaskDTO;
import com.List.ToDo.entities.TaskEntity;
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
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createTask(@PathVariable Long userId, @Valid @RequestBody TaskDTO dto) {
        try {
            TaskEntity task = taskService.createTask(userId, dto);
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro, não é permitido criar uma task sem usuário");
        }
    }

    @GetMapping("/show/{userId}")
    public ResponseEntity<List<TaskEntity>> getTasksByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
        TaskEntity updated = taskService.updateTask(id, dto);
        return ResponseEntity.ok(updated);
    }

    //	Deleting task
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Tarefa deletada com sucesso!");
    }

    //	Show all tasks
    @GetMapping("/showAll")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(taskService.showTasks());
    }

    //	Show all tasks by id
    @GetMapping("/showById/{id}")
    public ResponseEntity<?> getTasksById(@PathVariable Long id) {
        Optional<TaskEntity> task = taskService.showTasksById(id);
        if (task.isPresent()) {
            return ResponseEntity.ok(task.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa não encontrada");
        }
    }
}
