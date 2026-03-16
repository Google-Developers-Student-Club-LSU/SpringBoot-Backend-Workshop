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
@Table(name="user_session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSession {
    @Id
    @Column(name="session_id", nullable = false, updatable = false)
    private String userSession;
    @Column(name="user_id")
    private UUID userId; 
    
  
}
