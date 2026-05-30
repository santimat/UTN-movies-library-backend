package com.utntp.utnmovieslibrarybackend.service.review;

import com.utntp.utnmovieslibrarybackend.repository.review.JpaReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewDeleterService {
    private final JpaReviewRepository jpaReviewRepository;
    private final ReviewFinderService finder;


    public ReviewDeleterService(JpaReviewRepository jpaReviewRepository, ReviewFinderService finder) {
        this.jpaReviewRepository = jpaReviewRepository;
        this.finder = finder;
    }

    public void deleter(Long id){
        finder.find(id);
        jpaReviewRepository.deleteById(id);
    }
}
