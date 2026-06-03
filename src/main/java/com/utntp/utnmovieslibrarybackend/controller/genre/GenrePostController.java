package com.utntp.utnmovieslibrarybackend.controller.genre;

import com.utntp.utnmovieslibrarybackend.dto.request.genre.GenreRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.genre.GenreResponse;
import com.utntp.utnmovieslibrarybackend.mapper.genre.GenreMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.service.genre.GenreCreatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/genres")
public class GenrePostController {
    private final GenreCreatorService genreCreatorService;
    private final GenreMapper genreMapper;


    public GenrePostController(GenreCreatorService genreCreatorService) {
        this.genreCreatorService = genreCreatorService;
        this.genreMapper = new GenreMapper();
    }

    @PostMapping
    public ResponseEntity<GenreResponse> createGenre(@Valid @RequestBody GenreRequest genreRequest){
        Genre genre = genreCreatorService.create(genreRequest);
        GenreResponse genreResponse = genreMapper.toResponse(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreResponse);
    }
}
