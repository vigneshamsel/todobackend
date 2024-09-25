package com.todoapp.ErrorHandlings;

public class PasswordIncorrectException extends RuntimeException {
        public PasswordIncorrectException(String message){
            super(message);
        }


}
