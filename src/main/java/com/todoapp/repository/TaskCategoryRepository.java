package com.todoapp.repository;

import com.todoapp.modal.TaskCategory;
import com.todoapp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
    List<TaskCategory> findByUser(User user);
    Optional<TaskCategory> findByNameAndUser(String name, User user);

    Optional<TaskCategory> findByIdAndUser(Long id, User user);


}