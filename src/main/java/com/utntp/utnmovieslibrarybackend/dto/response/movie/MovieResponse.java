package com.utntp.utnmovieslibrarybackend.dto.response.movie;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieResponse {
    @NotNull
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String director;
    @NotBlank
    private String genre;
    @NotBlank
    private String synopsis;
    @NotNull
    private Integer year;
    @Nullable
    private String posterUrl;
    private Double averageRating;
    public MovieResponse() {
    }

    public MovieResponse(Long id, String title, String director, String genre, String synopsis, Integer year,
                         @Nullable String posterUrl, Double averageRating) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.synopsis = synopsis;
        this.year = year;
        this.posterUrl = posterUrl;
        this.averageRating = averageRating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Nullable
    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(@Nullable String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
