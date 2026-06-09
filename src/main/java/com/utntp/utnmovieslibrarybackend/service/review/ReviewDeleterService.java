package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewDeleterService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewFinderService reviewFinderService;


    public ReviewDeleterService(JpaReviewRepository jpaReviewRepository, ReviewFinderService reviewFinderService) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.reviewFinderService = reviewFinderService;
    }

    public void deleteById(Long id){
        reviewFinderService.findById(id);
        jpaReviewRepository.deleteById(id);
    }
}
