package com.todoapp.service;


import com.todoapp.ErrorHandlings.CategoryAlreadyExistsException;
import com.todoapp.ErrorHandlings.TaskCategoryNotFoundException;
import com.todoapp.modal.TaskCategory;
import com.todoapp.modal.User;
import com.todoapp.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoryService {
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<TaskCategory> getCategoriesForUser(User user) {
        List<TaskCategory> taskCategories= taskCategoryRepository.findByUser(user);
        if(taskCategories.isEmpty()){
            TaskCategory taskCategory= new TaskCategory();
            taskCategory.setName("General");
            taskCategory.setUser(user);
            taskCategoryRepository.save(taskCategory);
            return  taskCategoryRepository.findByUser(user);
        }
        return taskCategories;
    }

    public TaskCategory getCategoryById(Long id, User user) {
        TaskCategory category = taskCategoryRepository.findById(id).orElse(null);
        if (category != null && category.getUser().getId().equals(user.getId())) {
            return category;
        }
        return null;
    }

    public TaskCategory checkandCreateCategory(TaskCategory category, User user) {
        category.setUser(user);
        Optional<TaskCategory> existingCategory = taskCategoryRepository.findByNameAndUser(category.getName(), user);
        return existingCategory.orElseGet(() -> taskCategoryRepository.save(category));
    }

    public TaskCategory createCategory(TaskCategory category, User user) {
        category.setUser(user);
        Optional<TaskCategory> existingCategory = taskCategoryRepository.findByNameAndUser(category.getName(), user);
        if (existingCategory.isPresent()) {
            throw new CategoryAlreadyExistsException("Category with the same name already exists");
        }
        return taskCategoryRepository.save(category);
    }

    public boolean deleteCategory(Long id, User user) {
           Optional<TaskCategory> categoryOpt = taskCategoryRepository.findByIdAndUser(id, user);
           if (categoryOpt.isPresent()) {
               taskCategoryRepository.delete(categoryOpt.get());
               return true;
           } else {
               throw new TaskCategoryNotFoundException("task category Not found");
           }

    }

    public TaskCategory updateCategory(Long id, TaskCategory updatedCategory, User user) {
        Optional<TaskCategory> categoryOpt = taskCategoryRepository.findByIdAndUser(id, user);
        if (categoryOpt.isPresent()) {
            TaskCategory category = categoryOpt.get();
            category.setName(updatedCategory.getName());
            return taskCategoryRepository.save(category);
        }
        return null;
    }
}
