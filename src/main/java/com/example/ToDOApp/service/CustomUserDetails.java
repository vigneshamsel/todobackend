package com.example.ToDOApp.service;

import com.example.ToDOApp.modal.User;
import com.example.ToDOApp.modal.UserPrincipal;
import com.example.ToDOApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class CustomUserDetails implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user=userRepository.findUserByname(username).get();
        UserPrincipal userPrincipal= new UserPrincipal(user);
        return userPrincipal;
    }
}
