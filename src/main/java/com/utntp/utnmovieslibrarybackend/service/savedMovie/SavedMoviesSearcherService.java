package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
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

    public Page<SavedMovie> findAll(Pageable pageable){
        return jpaSavedMovieRepository.findAll(pageable);
    }
}


