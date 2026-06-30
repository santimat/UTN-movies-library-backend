package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieFinderByMovieIdService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;

    public SavedMovieFinderByMovieIdService(JpaSavedMovieRepository jpaSavedMovieRepository) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
    }

    public SavedMovie findByMovieId(Long id) {
        return jpaSavedMovieRepository.findByMovieId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Saved movie with id: " + id + " not found."));
    }

}
