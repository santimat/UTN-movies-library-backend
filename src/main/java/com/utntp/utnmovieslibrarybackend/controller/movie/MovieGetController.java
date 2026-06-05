package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.service.movie.MovieFinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movies")
public class MovieGetController {
    private final MovieFinderService movieFinderService;
    private final MovieMapper movieMapper;


    public MovieGetController(MovieFinderService movieFinderService ) {
        this.movieFinderService = movieFinderService;
        this.movieMapper = new MovieMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        Movie movie = movieFinderService.find(id);
        MovieResponse movieResponse = movieMapper.toResponse(movie);
        return ResponseEntity.ok(movieResponse);
    }
}
