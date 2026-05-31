package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieCreatorService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieMapper movieMapper;


    public MovieCreatorService(JpaMovieRepository jpaMovieRepository, MovieMapper movieMapper) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.movieMapper = movieMapper;
    }

    public Movie create(MovieRequest movieRequest){
        Movie movie = movieMapper.toEntity(movieRequest);
        return jpaMovieRepository.save(movie);
    }
}
