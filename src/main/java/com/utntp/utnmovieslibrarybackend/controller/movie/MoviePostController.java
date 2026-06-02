package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.service.movie.MovieCreatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movies")
public class MoviePostController {
    private final MovieCreatorService movieCreatorService;
    private final MovieMapper movieMapper;


    public MoviePostController(MovieCreatorService movieCreatorService) {
        this.movieCreatorService = movieCreatorService;
        this.movieMapper = new MovieMapper();
    }

    @PostMapping
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest){
        Movie  movie = movieCreatorService.create(movieRequest);
        MovieResponse movieResponse = movieMapper.toResponse(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }
}
