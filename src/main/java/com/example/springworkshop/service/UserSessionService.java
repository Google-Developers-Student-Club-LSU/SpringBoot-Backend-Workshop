package com.example.springworkshop.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.model.User;
import com.example.springworkshop.model.UserSession;
import com.example.springworkshop.repository.UserSessionRepository;

@Service
public class UserSessionService {
    public UserSessionService(UserSessionRepository userSessionRepository, UserService userService) {
        // TODO: constructor injection
    }

    public UserSession createSession(UUID userId) {
        // TODO: create and save a session
        return null;
    }

    public void deleteSession(String session) {
        // TODO: delete a session
    }

    public User findBySession(String session) {
        // TODO: load a user from a session id
        return null;
    }
}
