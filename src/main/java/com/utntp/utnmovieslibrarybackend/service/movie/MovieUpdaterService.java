package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieUpdaterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService movieFinderService;


    public MovieUpdaterService(JpaMovieRepository jpaMovieRepository, MovieFinderService movieFinderService) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.movieFinderService = movieFinderService;
    }

    public Movie update(Long id, MovieRequest movieRequest){
        Movie toUpdate = movieFinderService.find(id);
        toUpdate.setDirector(movieRequest.getDirector());
        toUpdate.setGenre(movieRequest.getGenre());
        toUpdate.setSynopsis(movieRequest.getSynopsis());
        toUpdate.setTitle(movieRequest.getTitle());
        toUpdate.setYear(movieRequest.getYear());
        toUpdate.setPosterUrl(movieRequest.getPosterUrl());
        return jpaMovieRepository.save(toUpdate);
    }
}
