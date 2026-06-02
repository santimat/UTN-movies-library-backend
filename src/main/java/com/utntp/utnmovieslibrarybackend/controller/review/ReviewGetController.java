package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewFinderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reviews")
public class ReviewGetController {
    private final ReviewFinderService reviewFinderService;
    private final ReviewMapper reviewMapper;


    public ReviewGetController(ReviewFinderService reviewFinderService) {
        this.reviewFinderService = reviewFinderService;
        this.reviewMapper = new ReviewMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable Long id){
        Review review = reviewFinderService.find(id);
        ReviewResponse reviewResponse = reviewMapper.toResponse(review);
        return ResponseEntity.ok(reviewResponse);
    }
}
