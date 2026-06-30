package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMoviesSearcherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies")
public class SavedMoviesGetController {
    private final SavedMoviesSearcherService savedMoviesSearcherService;
    private final MovieMapper movieMapper;

    public SavedMoviesGetController(SavedMoviesSearcherService savedMoviesSearcherService) {
        this.savedMoviesSearcherService = savedMoviesSearcherService;
        this.movieMapper = new MovieMapper();
    }

    @GetMapping
    public ResponseEntity<Page<MovieResponse>> findAllSavedMovies(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "5") int size,
                                                                  @RequestParam(required = false) String genre,
                                                                  @RequestParam(required = false) String searchText,
                                                                  @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> movies = savedMoviesSearcherService.findAll(pageable, userPrincipal.getId(), genre, searchText);
        return ResponseEntity.ok(
                movies.map(
                        movieMapper::toResponse
                )
        );
    }
}
