package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieCounterPerUserService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;

    public SavedMovieCounterPerUserService(JpaSavedMovieRepository jpaSavedMovieRepository) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
    }

    public Long getSavedMoviesPerUser(Long userId) {
        return jpaSavedMovieRepository.countByUserId(userId);
    }
}
