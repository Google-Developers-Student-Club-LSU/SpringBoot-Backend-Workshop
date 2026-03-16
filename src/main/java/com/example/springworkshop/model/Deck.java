package com.example.springworkshop.model;

import java.util.UUID;

import jakarta.annotation.Generated;
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
@Table(name="deck")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Deck {
    @Id
    @GeneratedValue
    @Column(name="deck_id", nullable = false, updatable = false)
    private UUID deckId;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="description", nullable = true)
    private String description;
    @Column(name="user_id", nullable = false)
    private UUID userID;


    public Deck( String name, UUID userId){
        this.name = name;
        this.userID = userId;
    }
    public Deck( String name, String description,UUID userId){
        this.name = name;
        this.description = description;
        this.userID = userId;
    }
}
