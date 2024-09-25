package com.todoapp.ErrorHandlings;

public class TaskCategoryNotFoundException extends RuntimeException {
    public TaskCategoryNotFoundException(String message){
        super(message);
    }

}
