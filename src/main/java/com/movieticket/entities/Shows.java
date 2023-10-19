package com.movieticket.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "Shows")
@NoArgsConstructor
@AllArgsConstructor
public class Shows {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    @ToString.Exclude
    @JsonBackReference
    private Theatre theatre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    @JsonBackReference
    private Movie movie;

    private LocalTime showstime;

    private LocalDate showsdate;

    private int price;

    private int totalseats;

    @PostLoad
    private void fillTransient() {
        if (theatre != null) {
            Long theaterId = theatre.getId();
        }
        if (movie != null) {
            Long movieId = movie.getId();
        }
    }

    public Long getTheaterId() {
        return theatre != null ? theatre.getId() : null;
    }



    public Long getMoviesId() {
        return movie != null ? movie.getId() : null;
    }



}
