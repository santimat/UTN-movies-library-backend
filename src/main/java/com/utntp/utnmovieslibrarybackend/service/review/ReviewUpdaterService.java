package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewUpdaterService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewFinderService reviewFinderService;


    public ReviewUpdaterService(JpaReviewRepository jpaReviewRepository, ReviewFinderService reviewFinderService) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.reviewFinderService = reviewFinderService;
    }

    public Review update(Long id, ReviewRequest reviewRequest){
        Review review = reviewFinderService.find(id);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setMovieId(reviewRequest.getMovieId());
        return jpaReviewRepository.save(review);
    }
}
