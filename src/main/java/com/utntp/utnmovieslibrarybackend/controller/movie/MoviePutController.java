package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.service.movie.MovieUpdaterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/movies")
public class MoviePutController {
    private final MovieUpdaterService movieUpdaterService;
    private final MovieMapper movieMapper;


    public MoviePutController(MovieUpdaterService movieUpdaterService, MovieMapper movieMapper) {
        this.movieUpdaterService = movieUpdaterService;
        this.movieMapper = movieMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> put(@PathVariable Long id,
                                             @Valid @RequestBody MovieRequest movieRequest){
        Movie movie = movieUpdaterService.update(id, movieRequest);
        MovieResponse movieResponse = movieMapper.toResponse(movie);
        return ResponseEntity.ok(movieResponse);
    }
}
