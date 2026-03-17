package com.example.springworkshop.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springworkshop.model.Card;

public interface CardRepository extends JpaRepository<Card, UUID> {
    List<Card> findAllByDeckId(UUID deckId);
}
