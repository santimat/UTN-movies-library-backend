package com.utntp.utnmovieslibrarybackend.dto.response.review;

public class ReviewResponse {
    private Long id;
    private Double rating;
    private String comment;
    private String movieTitle;
    private String username;
    private Long movieId;
    private Long userId;

    public ReviewResponse() {
    }

    public ReviewResponse(Long id, String movieTitle, Double rating, String comment, Long movieId, String username,
                          Long userId) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.userId = userId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
