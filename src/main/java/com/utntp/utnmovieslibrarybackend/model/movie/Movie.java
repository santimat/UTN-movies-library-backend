package com.utntp.utnmovieslibrarybackend.model.movie;

import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String synopsis;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    @Column(nullable = false)
    private String director;
    @Column(nullable = false)
    @Min(1888) // The year of the first movie ever made
    private Integer releaseYear;
    @Column()
    private String posterUrl;
    @Formula("(SELECT COALESCE(AVG(r.rating), 0.0) FROM reviews r WHERE r.movie_id = id)")
    private Double averageRating;
    @Column()
    private Double duration;
    @Column()
    private String trailerUrl;
    @Column()
    private String watchUrl;

    public Movie() {
    }

    public Movie(Long id, String title, String synopsis, Genre genre, String director, Integer releaseYear, String posterUrl
            , Double duration, String trailerUrl, @Nullable String watchUrl) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.director = director;
        this.releaseYear = releaseYear;
        this.posterUrl = posterUrl;
        this.duration = duration;
        this.trailerUrl = trailerUrl;
        this.watchUrl = watchUrl;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
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

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    @Nullable
    public String getWatchUrl() { return watchUrl; }

    public void setWatchUrl(@Nullable String watchUrl) { this.watchUrl = watchUrl; }
}
