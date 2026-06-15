package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.exception.DuplicateResourceException;
import com.utntp.utnmovieslibrarybackend.mapper.movie.MovieMapper;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.service.file.FileManagerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MovieCreatorService {
    private final JpaMovieRepository jpaMovieRepository;
    private final JpaGenreRepository jpaGenreRepository;
    private final FileManagerService fileManagerService;
    private final MovieMapper movieMapper;


    public MovieCreatorService(JpaMovieRepository jpaMovieRepository, JpaGenreRepository jpaGenreRepository, FileManagerService fileManagerService) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.jpaGenreRepository = jpaGenreRepository;
        this.fileManagerService = fileManagerService;
        this.movieMapper = new MovieMapper();
    }

    // ensure ACID properties of the transaction. If any error occurs, all database changes are rolled back
    @Transactional
    public Movie create(MovieRequest movieRequest) {

        if (jpaMovieRepository.existsByTitle(movieRequest.getTitle()))
            throw new DuplicateResourceException("Movie with title " + movieRequest.getTitle() + " already exists");

        boolean hasPosterUrl = movieRequest.getPosterUrl() != null && !movieRequest.getPosterUrl().trim().isEmpty();
        boolean hasPosterFile = movieRequest.getPosterFile() != null && !movieRequest.getPosterFile().isEmpty();

        if (!hasPosterUrl && !hasPosterFile)
            throw new IllegalArgumentException("Either posterUrl or posterFile must be provided");

        if (hasPosterUrl && hasPosterFile)
            throw new IllegalArgumentException("Have posterUrl and posterFile at the same time is not allowed");

        Genre savedGenre = jpaGenreRepository.findByName(movieRequest.getGenre()).
                orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setName(movieRequest.getGenre());
                    return jpaGenreRepository.save(newGenre);
                });

        String posterUrlToSave = movieRequest.getPosterUrl();
        if (hasPosterFile) {
            posterUrlToSave = fileManagerService.createFile(movieRequest.getPosterFile(), "posters/");
        }

        Movie movie = movieMapper.toEntity(movieRequest, savedGenre, posterUrlToSave);
        return jpaMovieRepository.save(movie);
    }
}
