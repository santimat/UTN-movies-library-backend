package com.utntp.utnmovieslibrarybackend.dto.request.genre;

import jakarta.validation.constraints.NotBlank;

public class GenreRequest {
    @NotBlank
    private String name;


    public GenreRequest() {}

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
