package com.utntp.utnmovieslibrarybackend.controller.genre;

import com.utntp.utnmovieslibrarybackend.dto.request.genre.GenreRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.genre.GenreResponse;
import com.utntp.utnmovieslibrarybackend.mapper.genre.GenreMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.service.genre.GenreUpdaterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/genres")
public class GenrePutController {
    private final GenreUpdaterService genreUpdaterService;
    private final GenreMapper genreMapper;

    public GenrePutController(GenreUpdaterService genreUpdaterService) {
        this.genreUpdaterService = genreUpdaterService;
        this.genreMapper = new GenreMapper();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenreResponse> updateGenreById(@PathVariable Long id,
                                                         @Valid @RequestBody GenreRequest genreRequest) {
        Genre genre = genreUpdaterService.update(id, genreRequest);
        GenreResponse genreResponse = genreMapper.toResponse(genre);
        return ResponseEntity.ok(genreResponse);
    }
}
