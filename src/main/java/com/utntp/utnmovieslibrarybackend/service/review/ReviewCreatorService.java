package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewCreatorService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewMapper reviewMapper;


    public ReviewCreatorService(JpaReviewRepository jpaReviewRepository, ReviewMapper reviewMapper) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public Review create(ReviewRequest request){
        Review review = reviewMapper.toEntity(request);
        return jpaReviewRepository.save(review);
    }
}
