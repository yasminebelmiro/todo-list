package com.yasminebelmiro.todo_list.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.yasminebelmiro.todo_list.repository.UserRepository;


public class AuthorizationService implements UserDetailsService{

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }
    
}