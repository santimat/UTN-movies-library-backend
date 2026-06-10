package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SavedMoviesSearcherService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;

    public SavedMoviesSearcherService(JpaSavedMovieRepository jpaSavedMovieRepository){
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
    }

    public Page<Movie> findAll(Pageable pageable, Long userId){
        return jpaSavedMovieRepository.findAllByUserId(userId, pageable);
    }
}


