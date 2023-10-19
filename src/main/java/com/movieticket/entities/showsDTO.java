package com.movieticket.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class showsDTO {

    private Long showsId;

    private Long movieId;

    private LocalTime showstime;

    private LocalDate showsdate;

    private int price;

    private int totalseats;


}
