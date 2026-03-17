package com.example.springworkshop.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.model.Deck;
import com.example.springworkshop.repository.DeckRepository;
import com.example.springworkshop.repository.UserRepository;

@Service
public class DeckService {
    public DeckService(DeckRepository deckRepository, UserRepository userRepository) {
        // TODO: constructor injection
    }

    public Deck createDeck(String deckName, String description, UUID userId) {
        // TODO: create and save a deck
        return null;
    }

    public List<Deck> findAllDeck(UUID userId) {
        // TODO: find all decks for a user
        return null;
    }

    public void deleteDeck(UUID deckID) {
        // TODO: delete a deck
    }
}
