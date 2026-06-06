package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.utils.BlankToNullUtility;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MoviesSearcherService {
    private final JpaMovieRepository jpaMovieRepository;
    private final BlankToNullUtility blankToNullUtility;


    public MoviesSearcherService(JpaMovieRepository jpaMovieRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.blankToNullUtility = new BlankToNullUtility();
    }

    public Page<Movie> findAll(Pageable pageable, @Nullable String genre, @Nullable String searchText){
        String searchTextLike = searchText != null ? searchText.replace("+", "%") : "";
        return jpaMovieRepository.findAllWithFilters(blankToNullUtility.blankToNull(genre),
                blankToNullUtility.blankToNull(searchTextLike),
                pageable);
    }
}
