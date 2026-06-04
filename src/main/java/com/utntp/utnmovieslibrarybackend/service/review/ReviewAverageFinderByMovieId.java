package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewAverageFinderByMovieId {
    private final JpaReviewRepository jpaReviewRepository;

    public ReviewAverageFinderByMovieId(JpaReviewRepository jpaReviewRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
    }

    public Double getAverageByMovieId(Long movieId){
        return jpaReviewRepository.findAverageRatingByMovieId(movieId);
    }
}
