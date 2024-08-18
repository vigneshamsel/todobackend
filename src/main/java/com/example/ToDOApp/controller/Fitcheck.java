package com.example.ToDOApp.controller;

import com.example.ToDOApp.DTO.UserTokenDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fitcheck")
@CrossOrigin("http://localhost:4200")
public class Fitcheck {


    @GetMapping("get")
    public String get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserTokenDto userDetails = (UserTokenDto) authentication.getPrincipal();
            String username = userDetails.getUserName();
            return "why it went  off "+username;// or another identifier
        }

}
