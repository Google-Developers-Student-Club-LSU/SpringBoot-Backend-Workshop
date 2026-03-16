package com.example.springworkshop.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.exception.ResourceNotFoundException;
import com.example.springworkshop.model.User;
import com.example.springworkshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository; 
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String name, String password){
        User user = new User(name, password);
        return userRepository.save(user);
    }

    public User loginUser(String name, String password){
        User user = userRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        return user;
    }

    public User findUserById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    
    
}
