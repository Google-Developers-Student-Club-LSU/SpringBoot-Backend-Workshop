package com.example.springworkshop.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.model.Card;
import com.example.springworkshop.repository.CardRepository;

@Service
public class CardService {
    public CardService(CardRepository cardRepository) {
        // TODO: constructor injection
    }

    public Card createCard(String question, String answer, UUID userId, UUID deckId) {
        // TODO: create and save a card
        return null;
    }

    public List<Card> findAllCardDeck(UUID deckId) {
        // TODO: find all cards for a deck
        return null;
    }

    public void deleteCard(UUID cardId) {
        // TODO: delete a card
    }
}
