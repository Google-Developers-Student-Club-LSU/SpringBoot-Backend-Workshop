package com.example.springworkshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springworkshop.controller.DTO.CreateCardReq;
import com.example.springworkshop.controller.DTO.CreateCardRes;
import com.example.springworkshop.model.Card;
import com.example.springworkshop.model.User;
import com.example.springworkshop.service.CardService;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/card")
public class CardController {

    private final CardService cardService;
    
    
    public CardController (CardService cardService){
        this.cardService = cardService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createCard(@PathVariable UUID id, @RequestBody CreateCardReq createCardReq, @AuthenticationPrincipal User user ) {
        Card card = new Card(createCardReq.question(), createCardReq.answer(), user.getUserId(), id);
        CreateCardRes createCardRes = new CreateCardRes(
            card.getQuestion(),
            card.getAnswer()
        );
        return new ResponseEntity<>(createCardRes, HttpStatus.OK);
    }

    @GetMapping("/{id}/deck")
    public ResponseEntity<?> getAllCardInDeck(@PathVariable UUID id) {
        List<Card> cards = cardService.findAllCardDeck(id);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeck(@PathVariable UUID id){
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
