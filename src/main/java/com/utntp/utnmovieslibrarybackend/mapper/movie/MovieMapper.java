package com.utntp.utnmovieslibrarybackend.mapper.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;

public class MovieMapper {

    public MovieMapper() {
    }

    public Movie toEntity(MovieRequest request, Genre genre, String posterUrlToSave) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setGenre(genre);
        movie.setSynopsis(request.getSynopsis());
        movie.setYear(request.getYear());
        movie.setPosterUrl(posterUrlToSave);
        movie.setDuration(request.getDuration());
        return movie;
    }

    public MovieResponse toResponse(Movie movie) {

        String posterUrl = movie.getPosterUrl().contains("uploads/posters") ?
                "http://localhost:8091/" + movie.getPosterUrl() : movie.getPosterUrl();
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getGenre().getName(),
                movie.getSynopsis(), movie.getYear(), posterUrl, movie.getAverageRating(),
                movie.getDuration(), movie.getTrailerUrl());
    }
}
