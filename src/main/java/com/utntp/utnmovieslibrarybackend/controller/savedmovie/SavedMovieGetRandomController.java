package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.dto.response.movie.MovieResponse;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMovieRandomFinderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies/random")
public class SavedMovieGetRandomController {
    private final SavedMovieRandomFinderService savedMovieRandomFinderService;
    private final MovieMapper movieMapper;

    public SavedMovieGetRandomController(SavedMovieRandomFinderService savedMovieRandomFinderService) {
        this.savedMovieRandomFinderService = savedMovieRandomFinderService;
        this.movieMapper = new MovieMapper();
    }

    @GetMapping()
    public MovieResponse getRandomSavedMovie(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Movie randomMovie = savedMovieRandomFinderService.findRandomSavedMovie(userPrincipal.getId());
        return movieMapper.toResponse(randomMovie);
    }
}
