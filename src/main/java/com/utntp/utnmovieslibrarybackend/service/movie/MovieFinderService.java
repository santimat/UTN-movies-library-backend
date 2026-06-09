package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieFinderService {
    private final JpaMovieRepository jpaMovieRepository;


    public MovieFinderService(JpaMovieRepository jpaMovieRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
    }

    public Movie findById(Long id){
        return jpaMovieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie with id " + id + " not found"));
    }
}
