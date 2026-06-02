package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MoviesSearcherService {
    private final JpaMovieRepository jpaMovieRepository;


    public MoviesSearcherService(JpaMovieRepository jpaMovieRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
    }

    public Page<Movie> findAll(Pageable pageable){
        return jpaMovieRepository.findAll(pageable);
    }
}
