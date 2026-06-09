package com.utntp.utnmovieslibrarybackend.controller.review;

import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewFinderByMovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
                                                           @PathVariable String movieId){
        Pageable pageable = PageRequest.of(page, size);
        Page<Review> reviews = reviewFinderByMovieService.findByMovieId(Long.valueOf(movieId),pageable);
        return ResponseEntity.ok(
                reviews.map(
                        reviewMapper::toResponse
                )
        );
    }
}
