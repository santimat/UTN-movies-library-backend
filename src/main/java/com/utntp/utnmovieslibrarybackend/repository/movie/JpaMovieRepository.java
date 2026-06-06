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
    @Query("SELECT m FROM Movie m WHERE LOWER(m.genre.name) = LOWER(:genreName)")
    Page<Movie> findAllByGenre(@Param(value = "genreName") String genre, Pageable pageable);
}
