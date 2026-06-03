package com.utntp.utnmovieslibrarybackend.service.genre;

import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreDeleterService {
    private final JpaGenreRepository jpaGenreRepository;
    private final GenreFinderService genreFinderService;


    public GenreDeleterService(JpaGenreRepository jpaGenreRepository, GenreFinderService genreFinderService) {
        this.jpaGenreRepository = jpaGenreRepository;
        this.genreFinderService = genreFinderService;
    }

    public void delete(Long id){
        genreFinderService.find(id);
        jpaGenreRepository.existsById(id);
    }
}
