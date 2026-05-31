package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.service.movie.MoviesSearcherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movies")
public class MoviesGetController {
    private final MoviesSearcherService moviesSearcherService;
    private final MovieMapper movieMapper;


    public MoviesGetController(MoviesSearcherService moviesSearcherService, MovieMapper movieMapper) {
        this.moviesSearcherService = moviesSearcherService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getAll(@RequestParam int page,
                                                      @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies = moviesSearcherService.findAll(pageable);
        return ResponseEntity.ok(
                movies.map(
                        movieMapper::toResponse
                )
        );
    }
}
