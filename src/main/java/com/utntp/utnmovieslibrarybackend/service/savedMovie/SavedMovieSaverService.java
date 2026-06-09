package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieSaverService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;
    private final JpaUserRepository jpaUserRepository;
    private final JpaMovieRepository jpaMovieRepository;

    public SavedMovieSaverService(JpaSavedMovieRepository jpaSavedMovieRepository, JpaUserRepository jpaUserRepository, JpaMovieRepository jpaMovieRepository) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
        this.jpaUserRepository = jpaUserRepository;
        this.jpaMovieRepository = jpaMovieRepository;
    }

}
