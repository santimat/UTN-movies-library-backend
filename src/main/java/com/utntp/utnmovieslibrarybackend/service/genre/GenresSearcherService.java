package com.utntp.utnmovieslibrarybackend.service.genre;

import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenresSearcherService {
    private final JpaGenreRepository jpaGenreRepository;


    public GenresSearcherService(JpaGenreRepository jpaGenreRepository) {
        this.jpaGenreRepository = jpaGenreRepository;
    }

    public Page<Genre> getAll(Pageable pageable){
        return jpaGenreRepository.findAll(pageable);
    }
}
