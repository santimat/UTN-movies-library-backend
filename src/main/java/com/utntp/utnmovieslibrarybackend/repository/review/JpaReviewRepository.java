package com.utntp.utnmovieslibrarybackend.repository.review;

import com.utntp.utnmovieslibrarybackend.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {
}
