package com.todoapp.ErrorHandlings;

public class UserAlreadyExistExceptions extends RuntimeException{
   public UserAlreadyExistExceptions(String message){
       super(message);
   }
}
