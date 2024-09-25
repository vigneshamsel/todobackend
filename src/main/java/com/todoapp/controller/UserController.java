package com.todoapp.controller;

import com.todoapp.DTO.CredentialDTo;
import com.todoapp.DTO.UserTokenDto;
import com.todoapp.config.UserAuthenticationProvider;
import com.todoapp.modal.User;
import com.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated

@RestController
@RequestMapping("/user")
@CrossOrigin(Constants.CrossOrigin)
public class UserController {

    private static final int TEST_TIMEOUT = 60000; // one minute per test

    @Autowired
    public UserService userService;

    @Autowired
    public UserAuthenticationProvider userAuthenticationProvider;



    @PostMapping("register")
    public ResponseEntity<UserTokenDto> register(@RequestBody @Valid CredentialDTo credentialDTo) {
        User user= userService.addUser(credentialDTo);
        UserTokenDto userTokenDto= new UserTokenDto();
        userTokenDto.setUserName(credentialDTo.name());
        userTokenDto.setToken(userAuthenticationProvider.createToken(user));
        return ResponseEntity.ok(userTokenDto);

    }

    @PostMapping("login")
    public ResponseEntity<UserTokenDto> login(@RequestBody   @Valid  CredentialDTo credentialDTo) {
         User user= userService.login(credentialDTo);
         UserTokenDto userTokenDto= new UserTokenDto();
         userTokenDto.setUserName(user.getName());
         userTokenDto.setToken(userAuthenticationProvider.createToken(user));
         return ResponseEntity.ok(userTokenDto);
    }

    @GetMapping("get")
    public String get() {
        return "dingdong";
    }
}