package com.utntp.utnmovieslibrarybackend.controller.genre;

import com.utntp.utnmovieslibrarybackend.dto.response.genre.GenreResponse;
import com.utntp.utnmovieslibrarybackend.mapper.genre.GenreMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.service.genre.GenresSearcherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genres")
public class GenresGetController {
    private final GenresSearcherService genresSearcherService;
    private final GenreMapper genreMapper;

    public GenresGetController(GenresSearcherService genresSearcherService) {
        this.genresSearcherService = genresSearcherService;
        this.genreMapper = new GenreMapper();
    }

    @GetMapping
    public ResponseEntity<Page<GenreResponse>> getGenres(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Genre> genres = genresSearcherService.findAll(pageable);
        return ResponseEntity.ok(
                genres.map(
                        genreMapper::toResponse
                )
        );
    }
}
