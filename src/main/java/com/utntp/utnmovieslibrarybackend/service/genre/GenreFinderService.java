package com.utntp.utnmovieslibrarybackend.service.genre;

import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreFinderService {
    private final JpaGenreRepository jpaGenreRepository;


    public GenreFinderService(JpaGenreRepository jpaGenreRepository) {
        this.jpaGenreRepository = jpaGenreRepository;
    }

    public Genre find(Long id){
        return jpaGenreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with id: " + id + " not found"));
    }
}
