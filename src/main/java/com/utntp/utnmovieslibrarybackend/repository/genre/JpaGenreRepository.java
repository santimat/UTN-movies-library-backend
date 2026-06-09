package com.utntp.utnmovieslibrarybackend.repository.genre;

import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {
    Optional<Genre> findByName(String name);
}
