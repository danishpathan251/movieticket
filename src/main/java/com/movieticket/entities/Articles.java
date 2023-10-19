package com.movieticket.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "articles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Articles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String image;


    private String categories;

    @Column(columnDefinition = "TEXT")
    private String content;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(); // Set the current date and time when the entity is persisted
    }

    // Getters and setters

}
