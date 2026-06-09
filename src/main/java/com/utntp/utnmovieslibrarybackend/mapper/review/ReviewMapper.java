package com.utntp.utnmovieslibrarybackend.mapper.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.model.user.User;

public class ReviewMapper {

    public ReviewMapper() {
    }

    public Review toEntity(ReviewRequest request, Movie movie, User user){
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setMovie(movie);
        review.setUser(user);
        return review;
    }

    public ReviewResponse toResponse(Review review){
        return new ReviewResponse(review.getId(), review.getMovie().getTitle(),review.getRating(),
                review.getComment(), review.getMovie().getId(), review.getUser().getName());
    }
}
