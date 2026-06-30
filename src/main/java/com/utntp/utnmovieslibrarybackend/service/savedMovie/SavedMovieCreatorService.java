package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.dto.request.savedmovie.SavedMovieRequest;
import com.utntp.utnmovieslibrarybackend.exception.DuplicateResourceException;
import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.mapper.savedmovie.SavedMovieMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieCreatorService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;
    private final JpaMovieRepository jpaMovieRepository;
    private final EntityManager entityManager;
    private final SavedMovieMapper savedMovieMapper;

    public SavedMovieCreatorService(JpaSavedMovieRepository jpaSavedMovieRepository, JpaMovieRepository jpaMovieRepository, EntityManager entityManager) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
        this.jpaMovieRepository = jpaMovieRepository;
        this.entityManager = entityManager;
        this.savedMovieMapper = new SavedMovieMapper();
    }

    public SavedMovie create(SavedMovieRequest savedMovieRequest, Long userId) {
        if (!jpaMovieRepository.existsById(savedMovieRequest.getMovieId())) {
            throw new ResourceNotFoundException("Movie with id: " + savedMovieRequest.getMovieId() + " not found.");
        }

        if (jpaSavedMovieRepository.existsByUserIdAndMovieId(userId, savedMovieRequest.getMovieId())) {
            throw new DuplicateResourceException("Movie with id: " + savedMovieRequest.getMovieId() + " is already saved by user with id: " + userId);
        }

        Movie movieProxy = entityManager.getReference(Movie.class, savedMovieRequest.getMovieId());
        User userProxy = entityManager.getReference(User.class, userId);
        SavedMovie savedMovie = savedMovieMapper.toEntity(movieProxy, userProxy);
        return jpaSavedMovieRepository.save(savedMovie);
    }


}
