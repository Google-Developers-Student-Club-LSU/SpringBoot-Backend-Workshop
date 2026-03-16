package com.example.springworkshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springworkshop.model.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, String> {
    
}
