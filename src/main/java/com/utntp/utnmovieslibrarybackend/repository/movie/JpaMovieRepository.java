package com.utntp.utnmovieslibrarybackend.repository.movie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
