package com.utntp.utnmovieslibrarybackend.service.genre;

import com.utntp.utnmovieslibrarybackend.dto.request.genre.GenreRequest;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreUpdaterService {
    private final JpaGenreRepository jpaGenreRepository;
    private final GenreFinderService genreFinderService;


    public GenreUpdaterService(JpaGenreRepository jpaGenreRepository, GenreFinderService genreFinderService) {
        this.jpaGenreRepository = jpaGenreRepository;
        this.genreFinderService = genreFinderService;
    }

    public Genre update(Long id, GenreRequest genreRequest){
        Genre toUpdate = genreFinderService.find(id);
        toUpdate.setName(genreRequest.getName());
        return jpaGenreRepository.save(toUpdate);
    }
}
