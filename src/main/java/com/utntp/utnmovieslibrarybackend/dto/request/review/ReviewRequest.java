package com.utntp.utnmovieslibrarybackend.dto.request.review;


import jakarta.validation.constraints.*;

public class ReviewRequest {

    @NotNull
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    private Double rating;
    @NotBlank
    @Size(min = 1, max = 500, message = "Comment must be between 1 and 500 characters")
    private String comment;
    @NotNull
    @Min(value = 1, message = "Movie ID must be a positive number")
    private Long movieId;

    public ReviewRequest() {
    }

    public ReviewRequest(Double rating, String comment, Long movieId) {
        this.rating = rating;
        this.comment = comment;
        this.movieId = movieId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }
}
