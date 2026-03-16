package com.example.springworkshop.helper;

import java.util.UUID;

public class GenerateSession {
    public static String generateId(){
        UUID uuid = UUID.randomUUID();

        String session = uuid.toString().replaceAll("-", "");

        return session;
    }
}
