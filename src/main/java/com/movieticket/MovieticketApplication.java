package com.movieticket;

import com.movieticket.dao.MovieRepository;
import com.movieticket.dao.TheatreRepository;
import com.movieticket.entities.Movie;
import com.movieticket.entities.Theatre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class MovieticketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieticketApplication.class, args);


//		Theatre theatre1 = new Theatre();
//		theatre1.setName("Mukta Art Cinemas");
//		theatre1.setLocation("Alkapuri");
//
//		Theatre theatre2 = new Theatre();
//		theatre2.setName("Pratap Cinema");
//		theatre2.setLocation("Nyay Mandir");

//		Theatre theatre3 = new Theatre();
//		theatre3.setName("INOX Cinema");
//		theatre3.setLocation("Race Course");
//
//		Movie movie1 = new Movie();
//		movie1.setTitle("Radhe");
//		movie1.setDescription("Radhe Salman Khan");
//		movie1.setReleaseDate(LocalDate.parse("2023-05-23"));
//
//		Movie movie2 = new Movie();
//		movie2.setTitle("Pathan");
//		movie2.setDescription("Shahrukh khan 2023");
//		movie2.setReleaseDate(LocalDate.parse("2023-05-23"));
//
//		movie1.setDuration(LocalTime.parse("02:00:00"));

//		List<Theatre> movietheatre = movie1.getTheatres();
//		movietheatre.add(theatre1);
//		movietheatre.add(theatre2);
//		movietheatre.add(theatre3);
//		List<Theatre> movietheatre1 = movie2.getTheatres();
//		movietheatre1.add(theatre1);
//		movietheatre1.add(theatre2);
//		movietheatre1.add(theatre3);

//		List<Movie> theatre1movie = theatre1.getMovies();
//		theatre1movie.add(movie1);
//		theatre1movie.add(movie2);
//
//		List<Movie> theatre2movie = theatre2.getMovies();
//		theatre2movie.add(movie1);
//		theatre2movie.add(movie2);
//
//		List<Movie> theatre3movie = theatre3.getMovies();
//		theatre3movie.add(movie1);
//		theatre3movie.add(movie2);
//
//
//		theatreRepository.save(theatre1);
//		theatreRepository.save(theatre2);
//		theatreRepository.save(theatre3);

	}

//	@Autowired
//	private static TheatreRepository theatreRepository;
//
//	@Autowired
//	private static MovieRepository movieRepository;

}
