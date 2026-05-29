package com.utntp.utnmovieslibrarybackend.model.movie;

import jakarta.persistence.*;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String synopsis;
    @Column(nullable = false)
    private String genre;
    @Column(nullable = false)
    private String director;
    @Column(nullable = false)
    private Integer year;
    @Column()
    private String posterUrl;

    public Movie() {
    }

    public Movie(Long id, String title, String synopsis, String genre, String director, Integer year, String posterUrl) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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
}
