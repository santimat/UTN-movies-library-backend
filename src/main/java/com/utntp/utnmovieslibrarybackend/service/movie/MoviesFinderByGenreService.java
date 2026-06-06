package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MoviesFinderByGenreService {
    private final JpaMovieRepository jpaMovieRepository;

    public MoviesFinderByGenreService(JpaMovieRepository jpaMovieRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
    }

    public Page<Movie> findAllByGenre(String genre, Pageable pageable){
        return jpaMovieRepository.findAllByGenre(genre,pageable);
    }
}
