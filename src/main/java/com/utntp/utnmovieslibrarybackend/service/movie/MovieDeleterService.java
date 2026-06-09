package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieDeleterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService movieFinderService;


    public MovieDeleterService(JpaMovieRepository jpaMovieRepository, MovieFinderService movieFinderService) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.movieFinderService = movieFinderService;
    }

    public void deleteById(Long id){
        movieFinderService.findById(id);
        jpaMovieRepository.deleteById(id);
    }
}
