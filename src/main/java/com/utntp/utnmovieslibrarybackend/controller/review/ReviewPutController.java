package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.request.review.ReviewRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewUpdaterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewPutController {
    private final ReviewUpdaterService reviewUpdaterService;
    private final ReviewMapper reviewMapper;


    public ReviewPutController(ReviewUpdaterService reviewUpdaterService) {
        this.reviewUpdaterService = reviewUpdaterService;
        this.reviewMapper = new ReviewMapper();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<ReviewResponse> updateReviewById(@PathVariable Long id,
                                              @Valid @RequestBody ReviewRequest reviewRequest){
        Review review = reviewUpdaterService.updateById(id, reviewRequest);
        ReviewResponse reviewResponse = reviewMapper.toResponse(review);
        return ResponseEntity.ok(reviewResponse);
    }
}
