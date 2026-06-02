package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.exception.review.ReviewNotFoundException;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewFinderService {
    private final JpaReviewRepository jpaReviewRepository;


    public ReviewFinderService(JpaReviewRepository jpaReviewRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
    }

    public Review find(Long id){
        return jpaReviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }
}
