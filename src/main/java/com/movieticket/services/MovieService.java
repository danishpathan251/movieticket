package com.movieticket.services;

import com.movieticket.dao.MovieRepository;
import com.movieticket.dao.TheatreRepository;
import com.movieticket.entities.Movie;
import com.movieticket.entities.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie assignMovietoTheaters(Long movieId, Long theaterId){
        Set<Theatre> theaterSet = null;
        Movie movie = movieRepository.findById(movieId).get();
        Theatre theater = theatreRepository.findById(theaterId).get();
        theaterSet = movie.getTheatreSet();
        theaterSet.add(theater);
        movie.setTheatreSet(theaterSet);
        movieRepository.save(movie);
        return movieRepository.save(movie);
    }

//    public String addImage(MultipartFile file) throws IOException {
//
//        Movie image = new Movie();
//        image.setImage(file.getBytes());
//        movieRepository.save(image);
//        return "Successfully add";
//
//    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        return movieOptional.orElse(null);
    }
}
