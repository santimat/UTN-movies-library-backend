package com.utntp.utnmovieslibrarybackend.repository.review;

import com.utntp.utnmovieslibrarybackend.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    @Query("SELECT r FROM reviews r JOIN FETCH r.movie WHERE r.movie.id = :movieId")
    List<Review> findByMovieId(@Param("movieId") Long movieId);

    @Query("SELECT r FROM reviews r JOIN FETCH r.user WHERE r.user.id = :userId")
    List<Review> findByUserId(@Param("userId") Long userId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.movie.id = :movieId")
    Double findAverageRatingByMovieId(@Param("movieId") Long movieId);
}
