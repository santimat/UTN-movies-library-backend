package com.utntp.utnmovieslibrarybackend.repository.review;

import com.utntp.utnmovieslibrarybackend.model.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    Page<Review> findByMovieId( Long movieId, Pageable pageable);

    Page<Review> findByUserId(Long userId, Pageable pageable);

    Optional<Double> findAvgRatingByMovieId(Long movieId);

}
