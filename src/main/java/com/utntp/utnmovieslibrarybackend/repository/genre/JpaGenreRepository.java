package com.utntp.utnmovieslibrarybackend.repository.genre;

import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Long>, JpaSpecificationExecutor<Genre> {
    @Query("SELECT g FROM Genre g WHERE LOWER(g.name) = LOWER(:name)")
    Optional<Genre> findByName(@Param("name") String name);
}
