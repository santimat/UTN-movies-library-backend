package com.utntp.utnmovieslibrarybackend.mapper.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;

public class MovieMapper {

    public MovieMapper() {
    }

    public Movie toEntity(MovieRequest request){
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setGenre(request.getGenre());
        movie.setSynopsis(request.getSynopsis());
        movie.setYear(request.getYear());
        movie.setPosterUrl(request.getPosterUrl());
        return movie;
    }

    public MovieResponse toResponse(Movie movie){
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getGenre(), movie.getSynopsis(), movie.getYear(), movie.getPosterUrl());
    }
}
