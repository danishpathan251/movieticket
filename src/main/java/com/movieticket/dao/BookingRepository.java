package com.movieticket.dao;

import com.movieticket.entities.Booking;
import com.movieticket.entities.Shows;
import com.movieticket.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {



    int countByShows(Shows shows);
//    List<Booking> findByUserid(Long userid);

    Optional<Booking> findById(Long id);

    Booking findByShowsIdAndSeatNumber(Long showId, int seatNumber);

//    List<Integer> findSeatNumbersByShowId(Long showId);

//    List<Integer> findSeatNumbersByShow(Long showId);

    @Query("SELECT b.seatNumber FROM Booking b WHERE b.shows.id = :showId")
    List<Integer> findSeatNumbersByShows(@Param("showId") Long showId);

    List<Booking> findByShows(Shows shows);

    List<Booking> findByUser(User user);

//    List<Booking> findByShowsAndUser(String shows, String user);
//    List<Integer> findSeatNumbersByShows(Long showId);
    List<Booking> findByUserAndShows(User user, Shows shows);



    }

