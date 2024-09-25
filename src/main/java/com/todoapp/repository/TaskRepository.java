package com.todoapp.repository;

import com.todoapp.modal.Task;
import com.todoapp.modal.User;
import com.todoapp.modal.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndCategory(User user, TaskCategory category);
}