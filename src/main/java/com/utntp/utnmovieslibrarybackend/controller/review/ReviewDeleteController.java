package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.service.review.ReviewDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewDeleteController {
    private final ReviewDeleterService reviewDeleterService;


    public ReviewDeleteController(ReviewDeleterService reviewDeleterService) {
        this.reviewDeleterService = reviewDeleterService;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long id){
        reviewDeleterService.deleteById(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
