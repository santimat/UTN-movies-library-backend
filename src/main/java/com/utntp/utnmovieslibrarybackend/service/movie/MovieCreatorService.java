package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieCreatorService {
    private final JpaMovieRepository jpaMovieRepository;
    private final JpaGenreRepository jpaGenreRepository;
    private final MovieMapper movieMapper;


    public MovieCreatorService(JpaMovieRepository jpaMovieRepository, JpaGenreRepository jpaGenreRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.jpaGenreRepository = jpaGenreRepository;
        this.movieMapper = new MovieMapper();
    }

    public Movie create(MovieRequest movieRequest) {

        Genre savedGenre = jpaGenreRepository.findByName(movieRequest.getGenre()).
                orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setName(movieRequest.getGenre());
                    return jpaGenreRepository.save(newGenre);
                });
        Movie movie = movieMapper.toEntity(movieRequest, savedGenre);
        return jpaMovieRepository.save(movie);
    }
}
