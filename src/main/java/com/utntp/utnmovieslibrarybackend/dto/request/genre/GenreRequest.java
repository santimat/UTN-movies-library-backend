package com.utntp.utnmovieslibrarybackend.dto.request.genre;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class GenreRequest {
    @NotBlank
    @Min(value = 3, message = "Genre name must be at least 3 characters long")
    private String name;

    public GenreRequest() {
    }

    public GenreRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
