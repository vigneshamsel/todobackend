package com.example.ToDOApp.ErrorHandlings;

public class PasswordIncorrectException extends RuntimeException {
        public PasswordIncorrectException(String message){
            super(message);
        }


}
