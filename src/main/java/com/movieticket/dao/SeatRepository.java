package com.movieticket.dao;

import com.movieticket.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {

}