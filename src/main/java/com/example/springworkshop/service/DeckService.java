package com.example.springworkshop.service;

import com.example.springworkshop.repository.UserRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.springworkshop.exception.ResourceNotFoundException;
import com.example.springworkshop.model.Deck;
import com.example.springworkshop.repository.DeckRepository;

@Service
public class DeckService {
    public final DeckRepository deckRepository;

    public DeckService (DeckRepository deckRepository, UserRepository userRepository){
        this.deckRepository = deckRepository;
    }

    public Deck createDeck(String deckName, String description,UUID userId){
        
        Deck deck = new Deck(
            deckName,
            description,
            userId
        );
        return deckRepository.save(
                deck
        );
    }

    

    public List<Deck> findAllDeck(UUID userId){
        return deckRepository.findAllByUserID(userId).orElseThrow(()-> new ResourceNotFoundException("Deck Not Found"));
    }

    public void deleteDeck (UUID deckID){
        deckRepository.deleteById(deckID);
    }

}
