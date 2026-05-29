package com.utntp.utnmovieslibrarybackend.model.review;

import jakarta.persistence.*;

@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rating;
    @Column(nullable = false)
    private String comment;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long movieId;
}
