package com.utntp.utnmovieslibrarybackend.controller.savedmovie;

import com.utntp.utnmovieslibrarybackend.dto.request.savedmovie.SavedMovieRequest;
import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import com.utntp.utnmovieslibrarybackend.service.savedMovie.SavedMovieCreatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/savedmovies")
public class SavedMoviePostController {
    private final SavedMovieCreatorService savedMovieCreatorService;

    public SavedMoviePostController(SavedMovieCreatorService savedMovieCreatorService){
        this.savedMovieCreatorService = savedMovieCreatorService;
    }

    @PostMapping
    public ResponseEntity<String> createSavedMovie(@Valid @RequestBody SavedMovieRequest savedMovieRequest,
                                                   @AuthenticationPrincipal UserPrincipal userPrincipal){
        savedMovieCreatorService.create(savedMovieRequest, userPrincipal.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body("The movie with id: " + savedMovieRequest.getMovieId() + "was saved succesfilly.");
    }
}
