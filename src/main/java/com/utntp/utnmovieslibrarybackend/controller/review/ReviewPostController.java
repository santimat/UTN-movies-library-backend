package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.jwt.JwtService;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewCreatorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reviews")
public class ReviewPostController {
    private final ReviewCreatorService reviewCreatorService;
    private final ReviewMapper reviewMapper;
    private final JwtService jwtService;

    public ReviewPostController(ReviewCreatorService reviewCreatorService, JwtService jwtService) {
        this.reviewCreatorService = reviewCreatorService;
        this.reviewMapper = new ReviewMapper();
        this.jwtService = jwtService;
    }


    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody ReviewRequest reviewRequest,
                                                       @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {

        String token = authorization.substring(7);
        Long userId = jwtService.getUserId(token);

        Review review = reviewCreatorService.create(reviewRequest, userId);
        ReviewResponse reviewResponse = reviewMapper.toResponse(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResponse);
    }
}
