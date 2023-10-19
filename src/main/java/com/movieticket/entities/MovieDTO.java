package com.movieticket.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String title;
    private String description;
    private LocalTime duration;
    private LocalDate releaseDate;

    private  String imageUrl;
    private Set<Long> theaterIds;

    // getters and setters
}
