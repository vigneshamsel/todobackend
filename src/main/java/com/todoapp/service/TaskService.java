package com.todoapp.service;

import com.todoapp.ErrorHandlings.TaskCategoryNotFoundException;
import com.todoapp.modal.Task;
import com.todoapp.modal.User;
import com.todoapp.modal.TaskCategory;
import com.todoapp.repository.TaskCategoryRepository;
import com.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskCategoryService taskCategoryService;

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> getTasksForUserAndCategory(User user, TaskCategory category) {
        return taskRepository.findByUserAndCategory(user, category);
    }

    public Task getTaskById(Long id, User user) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null && task.getUser().getId().equals(user.getId())) {
            return task;
        }
        return null;
    }

    public Task createTask( Task task, User user) {
        Optional<TaskCategory> taskCategory= taskCategoryRepository.findByIdAndUser(task.getCategoryId(),user);
        if(!taskCategory.isPresent()){
            TaskCategory generalTaskCategory = new TaskCategory();
            generalTaskCategory.setName("General");
            generalTaskCategory= taskCategoryService.checkandCreateCategory(generalTaskCategory,user);
            task.setCategory(generalTaskCategory);
        }
        task.setUser(user);
        try{
            return taskRepository.save(task);
        }catch (Exception exception){
                    throw exception;
        }
    }

    // Other methods...
}