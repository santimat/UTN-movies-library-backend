package com.utntp.utnmovieslibrarybackend.exception.genre;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException(Long id) {
        super("Genre with id:" + id + " not found.");
    }
}
