package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieFinderService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;

    public SavedMovieFinderService(JpaSavedMovieRepository jpaSavedMovieRepository){
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
    }

    public SavedMovie findById(Long id){
        return jpaSavedMovieRepository.findById(id)
                .orElseThrow (() -> new ResourceNotFoundException("Saved movie with id: " + id + " not found."));
    }

}
