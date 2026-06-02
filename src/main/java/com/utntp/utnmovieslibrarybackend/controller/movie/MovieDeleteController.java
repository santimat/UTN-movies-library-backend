package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.service.movie.MovieDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movies")
public class MovieDeleteController {
    private final MovieDeleterService movieDeleterService;


    public MovieDeleteController(MovieDeleterService movieDeleterService) {
        this.movieDeleterService = movieDeleterService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Long id){
        movieDeleterService.deleter(id);
        return ResponseEntity.noContent().build();
    }
}
