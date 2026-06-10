package com.utntp.utnmovieslibrarybackend.dto.request.movie;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class MovieRequest {

    @NotBlank
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters long")
    private String title;
    @NotBlank
    @Size(min = 5, max = 100, message = "Director must be between 5 and 100 characters long")
    private String director;
    @NotBlank
    @Size(min = 5, max = 50, message = "Genre must be between 5 and 50 characters long")
    private String genre;
    @NotBlank
    @Size(min = 10, max = 1000, message = "Synopsis must be between 10 and 1000 characters long")
    private String synopsis;
    @NotNull
    @Min(value = 1888, message = "Year must be no earlier than 1888")
    private Integer year;
    @Nullable
    @Size(min = 5, message = "Poster URL must be at least 5 characters long")
    private String posterUrl;

    @Nullable
    MultipartFile posterFile;

    @NotNull
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Double duration;
    @Nullable
    @Size(min = 5, message = "Trailer URL must be at least 5 characters long")
    private String trailerUrl;

    public MovieRequest() {
    }

    public MovieRequest(String title, String director, String genre, String synopsis, Integer year, Double duration,
                        @Nullable String posterUrl,@Nullable MultipartFile posterFile, @Nullable String trailerUrl) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.synopsis = synopsis;
        this.year = year;
        this.duration = duration;
        this.posterFile = posterFile;
        this.posterUrl = posterUrl;
        this.trailerUrl = trailerUrl;
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

    @Nullable
    public MultipartFile getPosterFile() {
        return posterFile;
    }

    public void setPosterFile(@Nullable MultipartFile posterFile) {
        this.posterFile = posterFile;
    }
}
