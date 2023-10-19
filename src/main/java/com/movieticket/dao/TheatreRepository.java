package com.movieticket.dao;

import com.movieticket.entities.Movie;
import com.movieticket.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    Set<Theatre> findAllByIdIn(Set<Long> theaterIds);
//    List<Theatre> findAllById(List<Theatre> theatres);
//List<Movie> findAllByIdIn(List<Long> theaterIds);
//List<Movie> findAllByIdIn(Set<Long> theaterIds);
}
