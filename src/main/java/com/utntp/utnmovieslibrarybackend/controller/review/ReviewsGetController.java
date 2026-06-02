package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewsSearcherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reviews")
public class ReviewsGetController {
    private final ReviewsSearcherService reviewsSearcherService;
    private final ReviewMapper reviewMapper;


    public ReviewsGetController(ReviewsSearcherService reviewsSearcherService) {
        this.reviewsSearcherService = reviewsSearcherService;
        this.reviewMapper = new ReviewMapper();
    }

    @GetMapping
    public ResponseEntity<Page<ReviewResponse>> getReviews(@RequestParam int page,
                                                       @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewsSearcherService.findAll(pageable);
        return ResponseEntity.ok(
                reviews.map(
                        reviewMapper::toResponse
        )
        );
    }
}
