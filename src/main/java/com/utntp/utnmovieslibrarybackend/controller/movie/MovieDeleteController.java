package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.service.movie.MovieDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieDeleteController {
    private final MovieDeleterService movieDeleterService;

    public MovieDeleteController(MovieDeleterService movieDeleterService) {
        this.movieDeleterService = movieDeleterService;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteMovieById(@PathVariable Long id){
        movieDeleterService.deleteById(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
