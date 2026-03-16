package com.example.springworkshop.exception;

import java.security.Timestamp;
import java.time.Instant;

public record ErrorResponse(
    Instant timestamp,
    int status,
    String errorMsg ) 
{}
