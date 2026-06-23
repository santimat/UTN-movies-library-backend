package com.utntp.utnmovieslibrarybackend.exception;

public class OnlySameUserException extends RuntimeException {
    public OnlySameUserException(String message) {
        super(message);
    }
}
