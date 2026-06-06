package com.utntp.utnmovieslibrarybackend.repository.movie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Query("""
    SELECT m FROM Movie m
    WHERE
        (:genre IS NULL OR LOWER(m.genre.name) = LOWER(CAST(:genre AS string)))
        AND (
            :searchText IS NULL OR
            (:searchText IS NULL OR LOWER(m.director) LIKE LOWER(CONCAT('%', CAST(:searchText AS string), '%')))
        OR (:searchText IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', CAST(:searchText AS string), '%')))
        )
    """)

    Page<Movie> findAllWithFilters(@Param(value = "genre") String genre, @Param(value = "searchText") String searchText,
                  Pageable pageable);

}
