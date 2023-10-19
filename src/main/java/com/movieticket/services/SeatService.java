package com.movieticket.services;

import com.movieticket.dao.SeatRepository;
import com.movieticket.entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    public SeatRepository seatRepository;


    public Seat addSeats(Seat seat){
        return seatRepository.save(seat);
    }

    public List<Seat>  getAllSeat(){
        return seatRepository.findAll();
    }
}
