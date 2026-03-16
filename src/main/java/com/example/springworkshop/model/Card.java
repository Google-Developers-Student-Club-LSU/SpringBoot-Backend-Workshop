package com.example.springworkshop.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @Column(name="card_id", unique = true, updatable = false, nullable = false)
    @GeneratedValue
    private UUID cardId;
    @Column(name="question",nullable =  false)
    private String question; 
    @Column(name="answer", nullable = false)
    private String answer; 
    @Column(name="user_id", nullable = false)
    private UUID userId;
    @Column(name="deck_id", nullable = false)
    private UUID deckId;

    public Card (String question, String answer, UUID userId, UUID deckId){
        this.question = question;
        this.answer = answer;
        this.userId = userId;
        this.deckId = deckId;
    }
    
}
