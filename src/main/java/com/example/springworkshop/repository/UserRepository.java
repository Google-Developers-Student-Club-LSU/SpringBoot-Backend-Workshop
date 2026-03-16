package com.example.springworkshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springworkshop.model.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByName(String name);
    
}
