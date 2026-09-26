package com.sports.backend.exception;

public class ClubNotFoundException extends RuntimeException{
    public ClubNotFoundException(String message) {
        super(message);
    }
}
