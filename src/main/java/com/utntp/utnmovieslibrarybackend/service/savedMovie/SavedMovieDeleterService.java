package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieDeleterService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;
    private final SavedMovieFinderService savedMovieFinderService;

    public SavedMovieDeleterService(JpaSavedMovieRepository jpaSavedMovieRepository, SavedMovieFinderService savedMovieFinderService){
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
        this.savedMovieFinderService = savedMovieFinderService;
    }

    public void deleteById(Long id){
        savedMovieFinderService.findById(id);
        jpaSavedMovieRepository.deleteById(id);
    }
}
