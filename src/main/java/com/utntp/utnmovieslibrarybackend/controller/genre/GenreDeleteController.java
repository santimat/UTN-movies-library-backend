package com.utntp.utnmovieslibrarybackend.controller.genre;

import com.utntp.utnmovieslibrarybackend.service.genre.GenreDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/genres")
public class GenreDeleteController {
    private final GenreDeleterService genreDeleterService;


    public GenreDeleteController(GenreDeleterService genreDeleterService) {
        this.genreDeleterService = genreDeleterService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable Long id){
        genreDeleterService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
