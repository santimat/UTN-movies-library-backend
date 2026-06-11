package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewFinderByMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies/reviews")
public class ReviewsGetByMovieIdController {
    private final ReviewFinderByMovieService reviewFinderByMovieService;
    private final ReviewMapper reviewMapper;

    public ReviewsGetByMovieIdController(ReviewFinderByMovieService reviewFinderByMovieService) {
        this.reviewFinderByMovieService = reviewFinderByMovieService;
        this.reviewMapper = new ReviewMapper();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Page<ReviewResponse>> getReviews(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "3") int size,
                                                           @PathVariable Long movieId) {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sortConfig = Sort.by(direction, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sortConfig);
        Page<Review> reviews = reviewFinderByMovieService.findByMovieId(movieId, pageable);
        return ResponseEntity.ok(
                reviews.map(
                        reviewMapper::toResponse
                )
        );
    }
}
