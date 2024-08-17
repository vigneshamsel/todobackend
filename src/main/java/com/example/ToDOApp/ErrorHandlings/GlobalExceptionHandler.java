package com.example.ToDOApp.ErrorHandlings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistExceptions.class)
    public ResponseEntity<Object> userAlreadyExistsHandling (UserAlreadyExistExceptions ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", "USER_ALREADY_EXISTS");
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound (UserNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("errorCode", "USER_NOT_EXISTS");
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
