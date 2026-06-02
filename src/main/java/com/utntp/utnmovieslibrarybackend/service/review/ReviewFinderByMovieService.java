package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewFinderByMovieService {
    private final JpaReviewRepository jpaReviewRepository;

    public ReviewFinderByMovieService(JpaReviewRepository jpaReviewRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
    }

    public Page<Review> getByMovieId(Long movieId, Pageable pageable){
        return jpaReviewRepository.findByMovieId(movieId, pageable);
    }
}
