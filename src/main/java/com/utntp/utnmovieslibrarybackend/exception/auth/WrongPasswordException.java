package com.utntp.utnmovieslibrarybackend.exception.auth;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("Wrong password");
    }
}
