package com.utntp.utnmovieslibrarybackend.dto.response.review;



public class ReviewResponse {
    private Long id;
    private Integer rating;
    private String comment;
    private Long movieId;


    public ReviewResponse() {
    }

    public ReviewResponse(Long id, Integer rating, String comment, Long movieId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.movieId = movieId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
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
