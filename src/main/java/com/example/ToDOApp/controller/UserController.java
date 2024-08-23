package com.example.ToDOApp.controller;

import com.example.ToDOApp.DTO.CredentialDTo;
import com.example.ToDOApp.DTO.UserTokenDto;
import com.example.ToDOApp.config.UserAuthenticationProvider;
import com.example.ToDOApp.modal.User;
import com.example.ToDOApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(Constants.Cr)
public class UserController {

    private static final int TEST_TIMEOUT = 60000; // one minute per test

    @Autowired
    public UserService userService;

    @Autowired
    public UserAuthenticationProvider userAuthenticationProvider;



    @PostMapping("register")
    public ResponseEntity<UserTokenDto> register(@RequestBody CredentialDTo credentialDTo) {
        userService.addUser(credentialDTo);
        UserTokenDto userTokenDto= new UserTokenDto();
        userTokenDto.setUserName(credentialDTo.name());
        userTokenDto.setToken(userAuthenticationProvider.createToken(userTokenDto));
        return ResponseEntity.ok(userTokenDto);

    }

    @PostMapping("login")
    public ResponseEntity<UserTokenDto> login(@RequestBody CredentialDTo credentialDTo) {
         User user= userService.login(credentialDTo);
         UserTokenDto userTokenDto= new UserTokenDto();
         userTokenDto.setUserName(user.getName());
         userTokenDto.setToken(userAuthenticationProvider.createToken(userTokenDto));
         return ResponseEntity.ok(userTokenDto);
    }

    @GetMapping("get")
    public String get() {
        return "dingdong";
    }
}