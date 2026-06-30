package com.utntp.utnmovieslibrarybackend.service.savedMovie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.savedMovie.JpaSavedMovieRepository;
import com.utntp.utnmovieslibrarybackend.utils.BlankToNullUtility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SavedMoviesSearcherService {
    private final JpaSavedMovieRepository jpaSavedMovieRepository;
    private final BlankToNullUtility blankToNullUtility;

    public SavedMoviesSearcherService(JpaSavedMovieRepository jpaSavedMovieRepository) {
        this.jpaSavedMovieRepository = jpaSavedMovieRepository;
        this.blankToNullUtility = new BlankToNullUtility();
    }

    public Page<Movie> findAll(Pageable pageable, Long userId, String genre, String searchText) {
        genre = blankToNullUtility.blankToNull(genre);
        searchText = blankToNullUtility.blankToNull(searchText);
        return jpaSavedMovieRepository.findMoviesByUserIdWithFilters(userId, genre, searchText, pageable);
    }
}


