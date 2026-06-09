package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.movie.Movie;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.movie.JpaMovieRepository;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class ReviewCreatorService {
    private final JpaReviewRepository jpaReviewRepository;
    private final JpaMovieRepository jpaMovieRepository;
    private final ReviewMapper reviewMapper;
    private final EntityManager entityManager;


    public ReviewCreatorService(JpaReviewRepository jpaReviewRepository, EntityManager entityManager,
                                JpaMovieRepository jpaMovieRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.jpaMovieRepository = jpaMovieRepository;
        this.entityManager = entityManager;
        this.reviewMapper = new ReviewMapper();
    }

    public Review create(ReviewRequest reviewRequest, Long userId){
        if(!jpaMovieRepository.existsById(reviewRequest.getMovieId()))
            throw new ResourceNotFoundException("Movie with id " + reviewRequest.getMovieId() + " not found");

        Movie movieProxy = entityManager.getReference(Movie.class, reviewRequest.getMovieId());
        User userProxy = entityManager.getReference(User.class, userId);
        Review review = reviewMapper.toEntity(reviewRequest, movieProxy, userProxy);
        return jpaReviewRepository.save(review);
    }
}
