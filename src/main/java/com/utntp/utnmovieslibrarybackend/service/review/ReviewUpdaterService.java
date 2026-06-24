package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.exception.OnlySameUserException;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import com.utntp.utnmovieslibrarybackend.service.auth.OnlySameUserValidatorService;
import org.springframework.stereotype.Service;

@Service
public class ReviewUpdaterService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewFinderService reviewFinderService;
    private final OnlySameUserValidatorService onlySameUserValidatorService;

    public ReviewUpdaterService(JpaReviewRepository jpaReviewRepository, ReviewFinderService reviewFinderService, OnlySameUserValidatorService onlySameUserValidatorService) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.reviewFinderService = reviewFinderService;
        this.onlySameUserValidatorService = onlySameUserValidatorService;
    }

    public Review updateById(Long id, ReviewRequest reviewRequest, Long jwtId) {
        Review review = reviewFinderService.findById(id);

        if (!onlySameUserValidatorService.isSameUser(jwtId, review.getUser().getId())) {
            throw new OnlySameUserException("You can only update your own reviews.");
        }

        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        return jpaReviewRepository.save(review);
    }
}

