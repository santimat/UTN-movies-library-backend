package com.utntp.utnmovieslibrarybackend.dto.request.movie;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieRequest {

    @NotBlank
    @Min(value = 5, message = "Title must be at least 5 characters long")
    @Max(value = 100, message = "Title must be at most 100 characters long")
    private String title;
    @NotBlank
    @Min(value = 5, message = "Director must be at least 5 characters long")
    @Max(value = 100, message = "Director must be at most 100 characters long")
    private String director;
    @NotBlank
    @Min(value = 5, message = "Genre must be at least 5 characters long")
    @Max(value = 100, message = "Genre must be at most 100 characters long")
    private String genre;
    @NotBlank
    @Min(value = 5, message = "Synopsis must be at least 5 characters long")
    @Max(value = 300, message = "Synopsis must be at most 100 characters long")
    private String synopsis;
    @NotNull
    @Min(value = 1888, message = "Year must be greater than or equal to 1888")
    @Max(value = 2100, message = "Year must be less than or equal to 2100")
    private Integer year;
    @Nullable
    @Min(value = 5, message = "Poster URL must be at least 5 characters long")
    private String posterUrl;
    @NotNull
    @Min(value = 1, message = "Duration must be greater than 1 or equal to 1 minute")
    private Double duration;
    @Nullable
    @Min(value = 5, message = "Trailer URL must be at least 5 characters long")
    private String trailerUrl;

    public MovieRequest() {
    }

    public MovieRequest(String title, String director, String genre, String synopsis, Integer year,
                        @Nullable String posterUrl, Double duration, @Nullable String trailerUrl) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.synopsis = synopsis;
        this.year = year;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
        this.duration = duration;
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
