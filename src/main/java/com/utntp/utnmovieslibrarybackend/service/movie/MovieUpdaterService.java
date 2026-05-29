package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieUpdaterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService finder;


    public MovieUpdaterService(JpaMovieRepository jpaMovieRepository, MovieFinderService finder) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.finder = finder;
    }

    public Movie update(Long id, MovieRequest request){
        Movie toUpdate = finder.find(id);
        toUpdate.setDirector(request.getDirector());
        toUpdate.setGenre(request.getGenre());
        toUpdate.setSynopsis(request.getSynopsis());
        toUpdate.setTitle(request.getTitle());
        toUpdate.setYear(request.getYear());
        toUpdate.setPosterUrl(request.getPosterUrl());
        return jpaMovieRepository.save(toUpdate);
    }
}
