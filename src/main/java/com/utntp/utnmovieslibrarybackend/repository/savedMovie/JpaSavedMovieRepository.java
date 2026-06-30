package com.utntp.utnmovieslibrarybackend.repository.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.savedMovie.SavedMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JpaSavedMovieRepository extends JpaRepository<SavedMovie, Long>, JpaSpecificationExecutor<SavedMovie> {
    // Page<SavedMovie> findAllByUserId(Long userId, Pageable pageable);

    @Query("""
            SELECT sm.movie FROM SavedMovie sm
            WHERE sm.user.id = :userId
                AND (:genre IS NULL OR LOWER(sm.movie.genre.name) = LOWER(CAST(:genre AS string)))
                AND (
                    :searchText IS NULL OR
                    LOWER(sm.movie.director) LIKE LOWER(CONCAT('%', CAST(:searchText AS string), '%'))
                    OR LOWER(sm.movie.title) LIKE LOWER(CONCAT('%', CAST(:searchText AS string), '%'))
                )
            """)
    Page<Movie> findMoviesByUserIdWithFilters(
            @Param("userId") Long userId,
            @Param("genre") String genre,
            @Param("searchText") String searchText,
            Pageable pageable
    );

    @Query("""
            SELECT sm.movie FROM SavedMovie sm
            WHERE sm.user.id = :userId
            ORDER BY FUNCTION('RANDOM')
            LIMIT 1
            """)
    Movie findRandomSavedMovieByUserId(@Param("userId") Long userId);

    boolean existsByUserIdAndMovieId(Long userId, Long movieId);

    Long countByUserId(Long userId);

    Optional<SavedMovie> findByMovieId(Long movieId);

    @Modifying
    @Transactional
    @Query("DELETE FROM SavedMovie sm WHERE sm.movie.id = :movieId AND sm.user.id = :userId")
    void deleteByMovieIdAndUserId(@Param("movieId") Long movieId, @Param("userId") Long userId);
}
