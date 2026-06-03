package com.utntp.utnmovieslibrarybackend.mapper.genre;

import com.utntp.utnmovieslibrarybackend.dto.request.genre.GenreRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.genre.GenreResponse;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;

public class GenreMapper {
    public Genre toEntity(GenreRequest genreRequest){
        Genre genre = new Genre();
        genre.setName(genreRequest.getName());
        return genre;
    }

    public GenreResponse toResponse(Genre genre){
        return new GenreResponse(genre.getId(), genre.getName());
    }
}
