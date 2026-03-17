package com.example.springworkshop.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.model.User;
import com.example.springworkshop.repository.UserRepository;

@Service
public class UserService {
    public UserService(UserRepository userRepository) {
        // TODO: constructor injection
    }

    public User createUser(String name, String password) {
        // TODO: create and save a user
        return null;
    }

    public User loginUser(String name, String password) {
        // TODO: authenticate a user
        return null;
    }

    public User findUserById(UUID id) {
        // TODO: find a user by id
        return null;
    }
}
