package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.service.movie.MoviesFinderByGenreService;
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
@RequestMapping("api/movies")
public class MoviesGetController {
    private final MoviesSearcherService moviesSearcherService;
    private final MoviesFinderByGenreService moviesFinderByGenreService;
    private final MovieMapper movieMapper;


    public MoviesGetController(MoviesSearcherService moviesSearcherService, MoviesFinderByGenreService moviesFinderByGenreService) {
        this.moviesSearcherService = moviesSearcherService;
        this.moviesFinderByGenreService = moviesFinderByGenreService;
        this.movieMapper = new MovieMapper();
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> getMovies(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                         @RequestParam(defaultValue = "title") String sortBy,
                                                         @RequestParam(defaultValue = "ASC") String sortOrder,
                                                         @RequestParam(required = false) String genre){

        Sort.Direction sortDirection = Sort.Direction.fromString(sortOrder);
        Sort sortConfig = Sort.by(sortDirection, sortBy);
        Pageable pageable = PageRequest.of(page, size, sortConfig);
        Page<Movie> movies = (genre == null || genre.isEmpty()) ? moviesSearcherService.findAll(pageable) :
                moviesFinderByGenreService.findAllByGenre(genre, pageable);

        return ResponseEntity.ok(
                movies.map(
                        movieMapper::toResponse
                )
        );
    }
}
