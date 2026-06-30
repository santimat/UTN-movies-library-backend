package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import com.utntp.utnmovieslibrarybackend.service.auth.OnlySameUserValidatorService;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieDeleterService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;
    private final SavedMovieFinderByMovieIdService savedMovieFinderByMovieIdService;
    private final OnlySameUserValidatorService onlySameUserValidatorService;

    public SavedMovieDeleterService(JpaSavedMovieRepository jpaSavedMovieRepository,
                                    SavedMovieFinderByMovieIdService savedMovieFinderByMovieIdService, OnlySameUserValidatorService onlySameUserValidatorService) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
        this.savedMovieFinderByMovieIdService = savedMovieFinderByMovieIdService;
        this.onlySameUserValidatorService = onlySameUserValidatorService;
    }

    public void deleteById(Long savedMovieId, Long userId) {
        SavedMovie savedMovie = savedMovieFinderByMovieIdService.findByMovieId(savedMovieId);
        onlySameUserValidatorService.isSameUser(userId, savedMovie.getUser().getId());
        jpaSavedMovieRepository.deleteByMovieIdAndUserId(savedMovieId, userId);
    }
}
