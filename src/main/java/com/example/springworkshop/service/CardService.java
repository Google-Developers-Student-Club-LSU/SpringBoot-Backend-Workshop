package com.example.springworkshop.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.springworkshop.exception.ResourceNotFoundException;
import com.example.springworkshop.model.Card;
import com.example.springworkshop.repository.CardRepository;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    public Card createCard (String question, String answer, UUID userId, UUID deckId){
        Card card = new Card(question, answer, userId, deckId);
        return cardRepository.save(card);
    }


    public List<Card> findAllCardDeck (UUID deckId){
        return cardRepository.findAllByDeckId(deckId).orElseThrow(()-> new ResourceNotFoundException("Card not found"));
    }



    public void deleteCard (UUID cardId){
        cardRepository.deleteById(cardId);
    }
}
