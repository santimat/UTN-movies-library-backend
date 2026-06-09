package com.utntp.utnmovieslibrarybackend.dto.request.genre;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class GenreRequest {
    @NotBlank
    @Size(min = 3, max = 50, message = "Genre name must be between 3 and 50 characters")
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
