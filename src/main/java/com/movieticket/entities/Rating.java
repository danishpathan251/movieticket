package com.movieticket.entities;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Constructors, getters, setters...
}
