package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMovieFinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies")
public class SavedMovieGetController {
    private final SavedMovieFinderService savedMovieFinderService;

    public SavedMovieGetController(SavedMovieFinderService savedMovieFinderService){
        this.savedMovieFinderService = savedMovieFinderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getSavedMovieById(@PathVariable Long id){
        savedMovieFinderService.findById(id);
        return ResponseEntity.ok("The movie with id: " + id + " was found succesfully.");
    }
}
