package com.movieticket.services;

import com.movieticket.dao.ShowsRepository;
import com.movieticket.entities.Movie;
import com.movieticket.entities.Shows;
import com.movieticket.entities.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShowsService {

    @Autowired
    private ShowsRepository showsRepository;

    public Shows addshows(Shows shows){
        return showsRepository.save(shows);
    }

//    public List<Shows> showTime(int mid){
//        return showsRepository.findByMid(mid);
//    }

    public List<Shows> getshowDetail(int id){
        return showsRepository.findById(id);
    }

    public List<Shows> showsAll(){
        return showsRepository.findAll();
    }

    public List<Shows> getShowsByTheaterMovieAndDate(Long theatreId, Long movieId, LocalDate showsDate) {
        return showsRepository.findByTheatreIdAndMovieIdAndShowsdate(theatreId, movieId, showsDate);
    }

    public Shows createShow(Shows show) {
        if (show.getTheatre() == null || show.getMovie() == null) {
            throw new IllegalArgumentException("Theater or movie not specified");
        }

        // Perform additional validations or business rules
        if (show.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }

        // Check if the theater and movie are associated
        Theatre theatre = show.getTheatre();
        Movie movie = show.getMovie();
        if (!theatre.getMovies().contains(movie)) {
            throw new IllegalArgumentException("Theater and movie not associated");
        }

        // Perform any other necessary operations before saving the show

        // Save the show
        return showsRepository.save(show);
    }




    public Shows getShowById(Long showId) {
        // Logic to retrieve the show by ID
        // Use appropriate repository or database queries
        return showsRepository.findById(showId).orElse(null);
    }
}
