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
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name="userId", nullable = false)
    private UUID userId;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @Column(name ="password", nullable = false)
    private String password;


    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
