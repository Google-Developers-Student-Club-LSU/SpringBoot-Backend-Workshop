package com.example.springworkshop.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.exception.ResourceNotFoundException;
import com.example.springworkshop.helper.GenerateSession;
import com.example.springworkshop.model.User;
import com.example.springworkshop.model.UserSession;
import com.example.springworkshop.repository.UserSessionRepository;

@Service
public class UserSessionService {
    private final UserSessionRepository userSessionRepository;
    private final UserService userService;

    public UserSessionService (UserSessionRepository userSessionRepository, UserService userService){
        this.userSessionRepository = userSessionRepository;
        this.userService = userService;
    }


    public UserSession createSession (UUID userId){
        String session = GenerateSession.generateId();
        UserSession userSession = new UserSession(
            session,
            userId
        );

        return userSessionRepository.save(userSession);
    }

    public void deleteSession (String session){
        userSessionRepository.deleteById(session);
    }

    public User findBySession (String session){
    UserSession userSession = userSessionRepository.findById(session).orElseThrow(()-> new ResourceNotFoundException("Session Not Found"));
    return userService.findUserById(userSession.getUserId());
    }
}
