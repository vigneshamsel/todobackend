package com.example.ToDOApp.ErrorHandlings;

public class UserAlreadyExistExceptions extends RuntimeException{
   public UserAlreadyExistExceptions(String message){
       super(message);
   }
}
