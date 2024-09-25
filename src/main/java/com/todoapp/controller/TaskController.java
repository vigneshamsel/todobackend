package com.todoapp.controller;


import com.todoapp.modal.Task;
import com.todoapp.repository.TaskRepository;
import com.todoapp.service.TaskCategoryService;
import com.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.todoapp.modal.User;
import com.todoapp.modal.TaskCategory;
import com.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@CrossOrigin(Constants.CrossOrigin)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskCategoryService taskCategoryService;

    @GetMapping
    public ResponseEntity<List<Task>> getUserTasks(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getTasksForUser(user));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable Long categoryId, @AuthenticationPrincipal User user) {
        TaskCategory category = taskCategoryService.getCategoryById(categoryId, user);
        if (category != null) {
            return ResponseEntity.ok(taskService.getTasksForUserAndCategory(user, category));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Task task = taskService.getTaskById(id, user);
        if (task != null) {
            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.createTask(task, user));
    }

}