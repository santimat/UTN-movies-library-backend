package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import com.utntp.utnmovieslibrarybackend.service.movie.MovieFinderService;
import org.springframework.stereotype.Service;

@Service
public class ReviewCreatorService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieFinderService movieFinderService;


    public ReviewCreatorService(JpaReviewRepository jpaReviewRepository, ReviewMapper reviewMapper, MovieFinderService movieFinderService) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.reviewMapper = reviewMapper;
        this.movieFinderService = movieFinderService;
    }

    public Review create(ReviewRequest reviewRequest){
        Movie movie = movieFinderService.find(reviewRequest.getMovieId());
        Review review = reviewMapper.toEntity(reviewRequest, movie);
        return jpaReviewRepository.save(review);
    }
}
