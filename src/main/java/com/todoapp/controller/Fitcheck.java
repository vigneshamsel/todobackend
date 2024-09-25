package com.todoapp.controller;

import com.todoapp.DTO.UserTokenDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/getuser")
@CrossOrigin(Constants.CrossOrigin)
public class Fitcheck {


    @GetMapping("get")
    public ResponseEntity<Map>  get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserTokenDto userDetails = (UserTokenDto) authentication.getPrincipal();
            String username = userDetails.getUserName();
        Map response = new HashMap<>();
        response.put("user",username);// Replace with actual response
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
