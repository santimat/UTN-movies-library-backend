package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMovieDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies")
public class SavedMovieDeleteController {
    private final SavedMovieDeleterService savedMovieDeleterService;

    public SavedMovieDeleteController(SavedMovieDeleterService savedMovieDeleterService) {
        this.savedMovieDeleterService = savedMovieDeleterService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSavedMovieById(@PathVariable Long id,
                                                       @AuthenticationPrincipal UserPrincipal userPrincipal) {

        savedMovieDeleterService.deleteById(id, userPrincipal.getId());
        return ResponseEntity.ok("Movie with id: " + id + " was deleted successfully.");
    }
}
