package com.utntp.utnmovieslibrarybackend.mapper.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.model.review.Review;

public class ReviewMapper {

    public ReviewMapper() {
    }

    public Review toEntity(ReviewRequest request){
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setMovieId(request.getMovieId());
        return review;
    }

    public ReviewResponse toResponse(Review review){
        return new ReviewResponse(review.getId(), review.getRating(), review.getComment(), review.getMovieId());
    }
}
