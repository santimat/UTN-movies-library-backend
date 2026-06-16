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
    private Integer releaseYear;
    @Nullable
    private String posterUrl;
    @NotNull
    private Double averageRating;
    @NotNull
    private Double duration;
    @Nullable
    private String trailerUrl;

    public MovieResponse() {
    }

    public MovieResponse(Long id, String title, String director, String genre, String synopsis, Integer releaseYear,
                         @Nullable String posterUrl, Double averageRating, Double duration, @Nullable String trailerUrl) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.synopsis = synopsis;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.averageRating = averageRating;
        this.duration = duration;
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

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Nullable
    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(@Nullable String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }
}
