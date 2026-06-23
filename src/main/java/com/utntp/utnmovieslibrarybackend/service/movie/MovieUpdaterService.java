package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieUpdaterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService movieFinderService;
    private final JpaGenreRepository jpaGenreRepository;

    public MovieUpdaterService(JpaMovieRepository jpaMovieRepository, MovieFinderService movieFinderService, JpaGenreRepository jpaGenreRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.movieFinderService = movieFinderService;
        this.jpaGenreRepository = jpaGenreRepository;
    }

    public Movie update(Long id, MovieRequest movieRequest) {
        Optional<Genre> existsGenre = jpaGenreRepository.findByName(movieRequest.getGenre());
        Movie toUpdate = movieFinderService.findById(id);
        if (existsGenre.isPresent()) toUpdate.setGenre(existsGenre.get());
        else {
            Genre newGenre = new Genre();
            newGenre.setName(movieRequest.getGenre());
            Genre savedGenre = jpaGenreRepository.save(newGenre);
            toUpdate.setGenre(savedGenre);
        }
        toUpdate.setDirector(movieRequest.getDirector());
        toUpdate.setSynopsis(movieRequest.getSynopsis());
        toUpdate.setTitle(movieRequest.getTitle());
        toUpdate.setReleaseYear(movieRequest.getReleaseYear());
        toUpdate.setPosterUrl(movieRequest.getPosterUrl());
        toUpdate.setWatchUrl(movieRequest.getWatchUrl());
        return jpaMovieRepository.save(toUpdate);
    }
}
