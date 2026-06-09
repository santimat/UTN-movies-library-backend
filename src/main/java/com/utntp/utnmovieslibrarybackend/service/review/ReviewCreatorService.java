package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import com.utntp.utnmovieslibrarybackend.service.movie.MovieFinderService;
import com.utntp.utnmovieslibrarybackend.service.user.UserFinderService;
import org.springframework.stereotype.Service;

@Service
public class ReviewCreatorService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewMapper reviewMapper;
    private final MovieFinderService movieFinderService;
    private final UserFinderService userFinderService;


    public ReviewCreatorService(JpaReviewRepository jpaReviewRepository,  MovieFinderService movieFinderService,
                                UserFinderService userFinderService) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.movieFinderService = movieFinderService;
        this.userFinderService = userFinderService;
        this.reviewMapper = new ReviewMapper();
    }

    public Review create(ReviewRequest reviewRequest, Long userId){
        Movie movie = movieFinderService.findById(reviewRequest.getMovieId());
        User user = userFinderService.findById(userId);
        Review review = reviewMapper.toEntity(reviewRequest, movie);
        review.setUser(user);
        return jpaReviewRepository.save(review);
    }
}
