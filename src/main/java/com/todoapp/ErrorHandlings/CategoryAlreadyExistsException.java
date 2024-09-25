package com.todoapp.ErrorHandlings;

public class CategoryAlreadyExistsException  extends RuntimeException{
    public CategoryAlreadyExistsException(String message){
        super(message);
    }

}
