package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.service.movie.MoviesSearcherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MoviesGetController {
    private final MoviesSearcherService moviesSearcherService;
    private final MovieMapper movieMapper;

    public MoviesGetController(MoviesSearcherService moviesSearcherService) {
        this.moviesSearcherService = moviesSearcherService;
        this.movieMapper = new MovieMapper();
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getAllMovies(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5") int size,
                                                            @RequestParam(defaultValue = "averageRating") String sortBy,
                                                            @RequestParam(defaultValue = "ASC") String sortOrder,
                                                            @RequestParam(required = false) String genre,
                                                            @RequestParam(required = false) String searchText) {
        Sort.Direction sortDirection = Sort.Direction.fromString(sortOrder);
        Sort sortConfig = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(page, size, sortConfig);
        Page<Movie> movies = moviesSearcherService.findAll(pageable, genre,
                searchText);

        return ResponseEntity.ok(
                movies.map(
                        movieMapper::toResponse
                )
        );
    }
}
