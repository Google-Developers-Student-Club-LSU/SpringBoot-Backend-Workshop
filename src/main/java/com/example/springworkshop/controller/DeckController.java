package com.example.springworkshop.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springworkshop.controller.DTO.CreateDeckReq;
import com.example.springworkshop.model.Deck;
import com.example.springworkshop.model.User;
import com.example.springworkshop.service.DeckService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/deck")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService){
        this.deckService = deckService;
    }


    @PostMapping
    public ResponseEntity<?> createDeck(@RequestBody CreateDeckReq createDeckReq, @AuthenticationPrincipal User user){
        Deck deck = deckService.createDeck(createDeckReq.name(), createDeckReq.description(), user.getUserId());
        return new ResponseEntity<>(deck.getDeckId(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllDeck(@AuthenticationPrincipal User user) {

        var decks = deckService.findAllDeck(user.getUserId());
        return new ResponseEntity<>(decks,HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDeck(@PathVariable UUID id){
        deckService.deleteDeck(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
