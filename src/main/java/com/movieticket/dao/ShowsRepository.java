package com.movieticket.dao;

import com.movieticket.entities.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowsRepository extends JpaRepository<Shows,Long> {

//    @Query("select s from Shows s where s.mid = ?1")
    List<Shows> findById(int id);

    List<Shows> findByTheatreIdAndMovieIdAndShowsdate(Long theatreId, Long movieId, LocalDate showsDate);

//    List<Shows> findByMovieAndTheatreAndShowsdate(Movie movie, Theatre theatre, LocalDate showsdate);
//    Optional<Shows> findByMid(int mid);
//Shows findByMovieAndTheatreAndShowsdate(Long movie, Long theatre, String showsdate);

//    Shows findByMovieAndTheatreAndShowsdate(Long movieId, Long theaterId, LocalDate showdate);
}
