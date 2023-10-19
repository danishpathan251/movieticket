package com.movieticket.entities;

import jakarta.persistence.*;

@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Constructors, getters, setters...
}

