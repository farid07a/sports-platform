package com.sports.backend.exception;

public class InvalidSearchException extends RuntimeException{

    public InvalidSearchException(String message) {
        super(message);
    }
}
