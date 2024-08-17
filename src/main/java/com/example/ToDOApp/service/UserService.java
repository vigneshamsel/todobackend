package com.example.ToDOApp.service;

import com.example.ToDOApp.DTO.CredentialDTo;
import com.example.ToDOApp.ErrorHandlings.UserAlreadyExistExceptions;
import com.example.ToDOApp.ErrorHandlings.UserNotFoundException;
import com.example.ToDOApp.modal.User;
import com.example.ToDOApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(CredentialDTo credentialDTo ) {
        if(userRepository.existsByname(credentialDTo.name())){
            throw new UserAlreadyExistExceptions("User Already Exists");
        }
        return  userRepository.save(new User(credentialDTo.name(),credentialDTo.password()));
    }

    public boolean isUserNameAlreadyExists(String name) {
        return  userRepository.existsByname(name);
    }


    public User login(CredentialDTo credentialDTo) {
        User user = userRepository.findUserByname(credentialDTo.name()).
                orElseThrow(()-> new UserNotFoundException("User Not found for this user name"));
        return user;
    }
}
