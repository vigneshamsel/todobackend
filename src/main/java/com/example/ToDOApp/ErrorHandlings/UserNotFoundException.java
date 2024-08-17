package com.example.ToDOApp.ErrorHandlings;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
