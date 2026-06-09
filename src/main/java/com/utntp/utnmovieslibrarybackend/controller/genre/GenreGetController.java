package com.utntp.utnmovieslibrarybackend.controller.genre;

import com.utntp.utnmovieslibrarybackend.dto.response.genre.GenreResponse;
import com.utntp.utnmovieslibrarybackend.mapper.genre.GenreMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.service.genre.GenreFinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/genres")
public class GenreGetController {
    private final GenreFinderService genreFinderService;
    private final GenreMapper genreMapper;


    public GenreGetController(GenreFinderService genreFinderService) {
        this.genreFinderService = genreFinderService;
        this.genreMapper = new GenreMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreResponse> getGenreById(@PathVariable Long id) {
        Genre genre = genreFinderService.findById(id);
        GenreResponse genreResponse = genreMapper.toResponse(genre);
        return ResponseEntity.ok(genreResponse);
    }
}
