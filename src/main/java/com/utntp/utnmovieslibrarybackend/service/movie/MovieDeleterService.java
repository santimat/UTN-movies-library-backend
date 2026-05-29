package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieDeleterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService finder;


    public MovieDeleterService(JpaMovieRepository jpaMovieRepository, MovieFinderService finder) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.finder = finder;
    }

    public void deleter(Long id){
        finder.find(id);
        jpaMovieRepository.deleteById(id);
    }
}
