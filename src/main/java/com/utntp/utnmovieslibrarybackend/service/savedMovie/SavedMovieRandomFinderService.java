package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieRandomFinderService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;

    public SavedMovieRandomFinderService(JpaSavedMovieRepository jpaSavedMovieRepository) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
    }

    public Movie findRandomSavedMovie(Long userId) {
        return jpaSavedMovieRepository.findRandomSavedMovieByUserId(userId);
    }
}
