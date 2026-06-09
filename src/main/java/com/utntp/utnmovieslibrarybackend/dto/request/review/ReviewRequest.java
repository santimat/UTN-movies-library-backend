package com.utntp.utnmovieslibrarybackend.dto.request.review;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Double rating;
    @NotBlank
    @Min(10)
    private String comment;
    @NotNull
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
