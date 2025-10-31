package com.todoapp.service;

import com.todoapp.ErrorHandlings.TaskCategoryNotFoundException;
import com.todoapp.ErrorHandlings.TaskNotFoundException;
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

    public boolean deleteTask(Long id, User user) {
        Optional<Task> task = taskRepository.findByIdAndUser(id, user);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            return true;
        } else {
            throw new TaskNotFoundException("task category Not found");
        }

    }

    public Task updateTask(Task task, User user) {
        Optional<Task> taskOptional = taskRepository.findByIdAndUser(task.getId(), user);
        if (taskOptional.isPresent()) {
            Task taskInDB = taskOptional.get();
            taskInDB.setTitle(task.getTitle());
            taskInDB.setCompleted(task.isCompleted());
            return taskRepository.save(taskInDB);
        }
        else {
            throw new TaskNotFoundException("task category Not found");
        }
    }

    public List<Task> searchTasksForUser(String query, User user) {
        // Simple search by title containing query (case insensitive)
        // You can extend to also search description or other fields
        return taskRepository.findByUserAndTitleContainingIgnoreCase(user, query);
    }

    // Other methods...
}