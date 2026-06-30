package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.dto.request.movie.MovieRequest;
import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.genre.JpaGenreRepository;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.service.file.FileManagerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieUpdaterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService movieFinderService;
    private final JpaGenreRepository jpaGenreRepository;
    private final FileManagerService fileManagerService;

    public MovieUpdaterService(JpaMovieRepository jpaMovieRepository, MovieFinderService movieFinderService,
                               JpaGenreRepository jpaGenreRepository, FileManagerService fileManagerService) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.movieFinderService = movieFinderService;
        this.jpaGenreRepository = jpaGenreRepository;
        this.fileManagerService = fileManagerService;
    }

    public Movie update(Long id, MovieRequest movieRequest) {
        Optional<Genre> existsGenre = jpaGenreRepository.findByName(movieRequest.getGenre());
        Movie toUpdate = movieFinderService.findById(id);
        if (existsGenre.isPresent()) {
            toUpdate.setGenre(existsGenre.get());
        } else {
            Genre newGenre = new Genre();
            newGenre.setName(movieRequest.getGenre());
            Genre savedGenre = jpaGenreRepository.save(newGenre);
            toUpdate.setGenre(savedGenre);
        }

        if (movieRequest.getWatchUrl() != null) {
            toUpdate.setWatchUrl(movieRequest.getWatchUrl());
        }

        if (movieRequest.getPosterFile() != null && !movieRequest.getPosterFile().isEmpty()) {
            if (movieRequest.getPosterUrl() != null && movieRequest.getPosterUrl().contains("uploads/posters")) {
                fileManagerService.deleteFile(movieRequest.getPosterUrl());
            }
            String posterUrlToSave = fileManagerService.createFile(movieRequest.getPosterFile(), "posters/");
            toUpdate.setPosterUrl(posterUrlToSave);
        }

        toUpdate.setDirector(movieRequest.getDirector());
        toUpdate.setSynopsis(movieRequest.getSynopsis());
        toUpdate.setTitle(movieRequest.getTitle());
        toUpdate.setDuration(movieRequest.getDuration());
        toUpdate.setReleaseYear(movieRequest.getReleaseYear());
        return jpaMovieRepository.save(toUpdate);
    }
}
