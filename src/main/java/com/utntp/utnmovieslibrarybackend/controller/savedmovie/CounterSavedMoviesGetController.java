package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.dto.response.savedMovie.SavedMovieCountResponse;
import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMovieCounterPerUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies/count")
public class CounterSavedMoviesGetController {
    private final SavedMovieCounterPerUserService savedMovieCounterPerUserService;

    public CounterSavedMoviesGetController(SavedMovieCounterPerUserService savedMovieCounterPerUserService) {
        this.savedMovieCounterPerUserService = savedMovieCounterPerUserService;
    }

    @GetMapping()
    public ResponseEntity<SavedMovieCountResponse> countSavedMovies(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Long quantity = savedMovieCounterPerUserService.getSavedMoviesPerUser(userPrincipal.getId());
        SavedMovieCountResponse response = new SavedMovieCountResponse(quantity);
        return ResponseEntity.ok(response);
    }
}
