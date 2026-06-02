package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewFinderByUserService {
    private final JpaReviewRepository jpaReviewRepository;

    public ReviewFinderByUserService(JpaReviewRepository jpaReviewRepository) {
        this.jpaReviewRepository = jpaReviewRepository;
    }

    public Page<Review> getByUserId(Long userId, Pageable pageable){
        return jpaReviewRepository.findByUserId(userId,pageable);
    }
}
