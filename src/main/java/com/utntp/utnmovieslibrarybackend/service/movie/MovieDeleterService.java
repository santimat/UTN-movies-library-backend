package com.utntp.utnmovieslibrarybackend.service.movie;

import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieDeleterService {
    private final JpaMovieRepository jpaMovieRepository;
    private final MovieFinderService movieFinderService;
    private final JpaReviewRepository jpaReviewRepository;

    public MovieDeleterService(JpaMovieRepository jpaMovieRepository, MovieFinderService movieFinderService, JpaReviewRepository jpaReviewRepository) {
        this.jpaMovieRepository = jpaMovieRepository;
        this.movieFinderService = movieFinderService;
        this.jpaReviewRepository = jpaReviewRepository;
    }

    public void deleteById(Long id) {
        movieFinderService.findById(id);
        jpaReviewRepository.deleteByMovieId(id);
        jpaMovieRepository.deleteById(id);
    }
}
