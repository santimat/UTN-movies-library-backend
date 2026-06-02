package com.utntp.utnmovieslibrarybackend.controller.movie;

import com.utntp.utnmovieslibrarybackend.dto.response.review.ReviewResponse;
import com.utntp.utnmovieslibrarybackend.mapper.review.ReviewMapper;
import com.utntp.utnmovieslibrarybackend.model.review.Review;
import com.utntp.utnmovieslibrarybackend.service.review.ReviewFinderByMovieService;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieReviewsGetController {
    private final ReviewFinderByMovieService reviewFinderByMovieService;
    private ReviewMapper reviewMapper;

    public MovieReviewsGetController(ReviewFinderByMovieService reviewFinderByMovieService) {
        this.reviewFinderByMovieService = reviewFinderByMovieService;
        this.reviewMapper = new ReviewMapper();
    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<Page<ReviewResponse>> getReviewsByMovieId(@PathVariable Long movieId,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Review> reviews = reviewFinderByMovieService.getByMovieId(movieId, pageable);
        return ResponseEntity.ok(reviews.map(review -> reviewMapper.toResponse(review)));
    }
}
