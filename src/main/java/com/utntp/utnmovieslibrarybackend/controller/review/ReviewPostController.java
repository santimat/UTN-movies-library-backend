package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.security.UserPrincipal;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewCreatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewPostController {
    private final ReviewCreatorService reviewCreatorService;
    private final ReviewMapper reviewMapper;

    public ReviewPostController(ReviewCreatorService reviewCreatorService) {
        this.reviewCreatorService = reviewCreatorService;
        this.reviewMapper = new ReviewMapper();
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody ReviewRequest reviewRequest,
                                                       @AuthenticationPrincipal UserPrincipal userPrincipal){
        Review review = reviewCreatorService.create(reviewRequest, userPrincipal.getId());
        ReviewResponse reviewResponse = reviewMapper.toResponse(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }
}
