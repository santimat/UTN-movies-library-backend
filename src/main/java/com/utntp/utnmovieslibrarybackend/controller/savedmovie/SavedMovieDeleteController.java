package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMovieDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies")
public class SavedMovieDeleteController {
    private final SavedMovieDeleterService savedMovieDeleterService;

    public SavedMovieDeleteController(SavedMovieDeleterService savedMovieDeleterService){
        this.savedMovieDeleterService = savedMovieDeleterService;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<String> deleteSavedMovieById(@PathVariable Long id){
        savedMovieDeleterService.deleteById(id);
        return ResponseEntity.ok("Movie with id: " + id + " was deleted succesfully.");
    }
}
