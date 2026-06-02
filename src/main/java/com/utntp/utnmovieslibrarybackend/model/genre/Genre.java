package com.utntp.utnmovieslibrarybackend.model.genre;

import jakarta.persistence.*;


@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    public Genre(){}

    public Genre(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){return id;}
    public String getName(){return name;}
    public void setId(Long id){this.id = id;}
    public void setName(String name){this.name = name;}
}
