package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewCreatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reviews")
public class ReviewPostController {
    private final ReviewCreatorService reviewCreatorService;
    private final ReviewMapper reviewMapper;

    public ReviewPostController(ReviewCreatorService reviewCreatorService, ReviewMapper reviewMapper) {
        this.reviewCreatorService = reviewCreatorService;
        this.reviewMapper = reviewMapper;
    }


    @PostMapping
    public ResponseEntity<ReviewResponse> post(@Valid @RequestBody ReviewRequest reviewRequest){
        Review review = reviewCreatorService.create(reviewRequest);
        ReviewResponse reviewResponse = reviewMapper.toResponse(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }
}
