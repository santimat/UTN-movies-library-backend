package com.utntp.utnmovieslibrarybackend.model.movie;

import com.utntp.utnmovieslibrarybackend.model.genre.Genre;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.Formula;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String synopsis;
    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    @Column(nullable = false)
    private String director;
    @Column(nullable = false)
    @Min(1888) // The year of the first movie ever made
    private Integer year;
    @Column()
    private String posterUrl;
    @Formula("(SELECT COALESCE(AVG(r.rating), 0.0) FROM reviews r WHERE r.movie_id = id)")
    private Double averageRating;


    public Movie() {
    }

    public Movie(Long id, String title, String synopsis, Genre genre, String director, Integer year, String posterUrl) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.posterUrl = posterUrl;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
}
