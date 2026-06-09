package com.utntp.utnmovieslibrarybackend.dto.request.savedmovie;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SavedMovieRequest {
    @NotNull
    @Min(value = 1, message = "Movie ID must be a positive number.")
    private Long movieId;

    public SavedMovieRequest(){}

    public SavedMovieRequest(Long movieId){
        this.movieId = movieId;
    }

    public Long getMovieId(){return movieId;}
    public void setMovieId(Long movieId){this.movieId = movieId;}
}
