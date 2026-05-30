package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewUpdaterService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewFinderService finder;


    public ReviewUpdaterService(JpaReviewRepository jpaReviewRepository, ReviewFinderService finder) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.finder = finder;
    }

    public Review update(Long id, ReviewRequest request){
        Review review = finder.find(id);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setMovieId(request.getMovieId());
        return jpaReviewRepository.save(review);
    }
}
