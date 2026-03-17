package com.example.springworkshop.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springworkshop.controller.DTO.CreateCardReq;
import com.example.springworkshop.model.User;
import com.example.springworkshop.service.CardService;

@RestController
@RequestMapping("/card")
public class CardController {

    public CardController(CardService cardService) {
        // TODO: constructor injection
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createCard(
            @PathVariable UUID id,
            @RequestBody CreateCardReq createCardReq,
            @AuthenticationPrincipal User user) {
        // TODO: create a card
        return null;
    }

    @GetMapping("/{id}/deck")
    public ResponseEntity<?> getAllCardInDeck(@PathVariable UUID id) {
        // TODO: get all cards in a deck
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDeck(@PathVariable UUID id) {
        // TODO: delete a card
        return null;
    }
}
