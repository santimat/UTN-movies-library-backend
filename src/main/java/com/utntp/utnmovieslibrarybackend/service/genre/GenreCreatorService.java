package com.utntp.utnmovieslibrarybackend.service.genre;

import com.utntp.utnmovieslibrarybackend.dto.request.genre.GenreRequest;
import com.utntp.utnmovieslibrarybackend.mapper.genre.GenreMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreCreatorService {
    private final JpaGenreRepository jpaGenreRepository;
    private final GenreMapper genreMapper;


    public GenreCreatorService(JpaGenreRepository jpaGenreRepository) {
        this.jpaGenreRepository = jpaGenreRepository;
        this.genreMapper = new GenreMapper();
    }

    public Genre create(GenreRequest genreRequest){
        Genre genre = genreMapper.toEntity(genreRequest);
        return jpaGenreRepository.save(genre);
    }
}
