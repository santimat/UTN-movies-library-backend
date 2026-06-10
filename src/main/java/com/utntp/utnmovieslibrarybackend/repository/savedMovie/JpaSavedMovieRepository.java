package com.utntp.utnmovieslibrarybackend.repository.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSavedMovieRepository extends JpaRepository<SavedMovie, Long>, JpaSpecificationExecutor<SavedMovie> {
    Page<Movie> findAllByUserId(Long userId, Pageable pageable);
    boolean existsByUserIdAndMovieId(Long userId, Long movieId);
    void deleteByUserIdAndMovieId(Long userId, Long movieId);
}
