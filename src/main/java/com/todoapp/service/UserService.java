package com.todoapp.service;

import com.todoapp.DTO.CredentialDTo;
import com.todoapp.ErrorHandlings.PasswordIncorrectException;
import com.todoapp.ErrorHandlings.UserAlreadyExistExceptions;
import com.todoapp.ErrorHandlings.UserNotFoundException;
import com.todoapp.modal.User;
import com.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(!user.getPassword().equals(credentialDTo.password())){
            throw new PasswordIncorrectException("Entered password was incorrect");
        }
        return user;
    }
}
