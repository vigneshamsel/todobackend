package com.todoapp.repository;

import com.todoapp.modal.Task;
import com.todoapp.modal.User;
import com.todoapp.modal.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndCategory(User user, TaskCategory category);
    Optional<Task> findByIdAndUser(Long id, User user);
    // Add this method in your TaskRepository interface

    List<Task> findByUserAndTitleContainingIgnoreCase(User user, String title);


}