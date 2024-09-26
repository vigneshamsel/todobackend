package com.todoapp.controller;

import com.todoapp.ErrorHandlings.CategoryAlreadyExistsException;
import com.todoapp.modal.TaskCategory;
import com.todoapp.modal.User;
import com.todoapp.service.TaskCategoryService;
import com.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(Constants.CrossOrigin)


public class TaskCategoryController {
    @Autowired
    private TaskCategoryService taskCategoryService;

    @GetMapping
    public ResponseEntity<List<TaskCategory>> getUserCategories(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskCategoryService.getCategoriesForUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCategory> getCategoryById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        TaskCategory category = taskCategoryService.getCategoryById(id, user);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TaskCategory> createCategory(@RequestBody TaskCategory category, @AuthenticationPrincipal User user) {
        TaskCategory taskCategory= taskCategoryService.createCategory(category, user);
        return ResponseEntity.ok(taskCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskCategory> deletedbyCategory(@PathVariable Long id, @AuthenticationPrincipal User user) {
        boolean isDeleted = taskCategoryService.deleteCategory(id,user);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCategory> updateCategory(@PathVariable Long id, @RequestBody TaskCategory updatedCategory, @AuthenticationPrincipal User user) {
        TaskCategory taskCategory = taskCategoryService.updateCategory(id, updatedCategory, user);
        if (taskCategory != null) {
            return ResponseEntity.ok(taskCategory);
        }
        return ResponseEntity.notFound().build();  // 404 Not Found
    }
}
