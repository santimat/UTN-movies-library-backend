package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewAverageFinderByMovieId {
    private final JpaReviewRepository jpaReviewRepository;

    public ReviewAverageFinderByMovieId(JpaReviewRepository jpaReviewRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
    }

    public Double getAverageByMovieId(Long movieId){
        return jpaReviewRepository.findAvgRatingByMovieId(movieId).orElseThrow(()-> new ResourceNotFoundException(
                "Average rating for movie with id:" + movieId + " not found."));
    }
}
