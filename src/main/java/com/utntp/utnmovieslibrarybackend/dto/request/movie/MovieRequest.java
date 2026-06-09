package com.utntp.utnmovieslibrarybackend.dto.request.movie;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieRequest {

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
    @NotNull
    private Double duration;
    @Nullable
    private String trailerUrl;

    public MovieRequest() {
    }
    public MovieRequest( String title, String director, String genre, String synopsis, Integer year,
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
