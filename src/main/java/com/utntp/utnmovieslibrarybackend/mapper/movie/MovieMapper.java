package com.utntp.utnmovieslibrarybackend.mapper.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import org.springframework.beans.factory.annotation.Value;

public class MovieMapper {
    @Value("${app.backend.url}")
    private String backend_url;

    public MovieMapper() {
    }

    public Movie toEntity(MovieRequest request, Genre genre, String posterUrlToSave) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setGenre(genre);
        movie.setSynopsis(request.getSynopsis());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setPosterUrl(posterUrlToSave);
        movie.setDuration(request.getDuration());
        movie.setTrailerUrl(request.getTrailerUrl());
        movie.setWatchUrl(request.getWatchUrl());
        return movie;
    }

    public MovieResponse toResponse(Movie movie) {

        String watchUrl = null;
        if (movie.getWatchUrl() != null) {
            watchUrl = movie.getWatchUrl().contains("uploads/posters") ?
                    "http://localhost:8091/" + movie.getWatchUrl() : movie.getWatchUrl();
        }
        String posterUrl = movie.getPosterUrl().contains("uploads/posters") ?
                "http://localhost:8091/" + movie.getPosterUrl() : movie.getPosterUrl();
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getGenre().getName(),
                movie.getSynopsis(), movie.getReleaseYear(), posterUrl, movie.getAverageRating(),
                movie.getDuration(), movie.getTrailerUrl(), watchUrl);
    }

    public String buildPosterUrl(String url) {
        if (url == null) return null;
        if (url.startsWith("https://") || url.startsWith("http://")) return url;
        return backend_url + "/uploads/posters/" + url;
    }
}
