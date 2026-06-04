package com.utntp.utnmovieslibrarybackend.dto.response.review;



public class ReviewResponse {
    private Long id;
    private String movieTitle;
    private Integer rating;
    private String comment;
    private Long movieId;
    private String username;

    public ReviewResponse() {
    }

    public ReviewResponse(Long id,String movieTitle, Integer rating, String comment, Long movieId, String username) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.username = username;
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
}
