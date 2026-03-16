package com.example.springworkshop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springworkshop.model.Deck;

public interface DeckRepository extends JpaRepository<Deck, UUID>{
    Optional<List<Deck>> findAllByUserID(UUID userID);
    
}
