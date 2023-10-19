package com.movieticket.controller;


import com.movieticket.dao.BookingRepository;
import com.movieticket.dao.MovieRepository;
import com.movieticket.dao.ShowsRepository;
import com.movieticket.dao.TheatreRepository;
import com.movieticket.entities.*;
import com.movieticket.payload.FileResponse;
import com.movieticket.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*")
public class PublicController {

    private static final String UPLOAD_FOLDER = "/image";
    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowsService showsService;

    @Autowired
    private BookingRepository bookingRepository;



    @Autowired
    private BookingService bookingService;
    @GetMapping("Message")
    public String msg() {
        return "THis is for testing";
    }

    @Autowired
    private  TheatreService theatreService;

    @Autowired
    private MovieService movieService;


    @CrossOrigin(origins = "*")
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity.ok(movies);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getshows/{id}")
     public Optional<Shows> findShows(@PathVariable Long id){
        return showsRepository.findById(id);

    }

    @GetMapping("/theaters")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<Theatre>> getAllTheaters() {
        List<Theatre> theaters = theatreRepository.findAll();
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/shows")
    public ResponseEntity<List<Shows>> getAllShows() {
        List<Shows> shows = showsRepository.findAll();
        return ResponseEntity.ok(shows);
    }



    @CrossOrigin(origins = "*")
    @GetMapping("/movielist/{mid}")
    public List<Movie> getMovieList(@PathVariable long mid) {
        return movieRepository.findAllById(mid);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/theatrelist/{id}")
    public Optional<Theatre> getTheatreList(@PathVariable long id) {
        return theatreRepository.findById(id);
    }


//    Get Showstime
    @CrossOrigin(origins = "*")
    @GetMapping("/{theatreId}/{movieId}/{showsDate}")
    public ResponseEntity<List<Shows>> getShowsByTheaterMovieAndDate(
            @PathVariable Long theatreId,
            @PathVariable Long movieId,
            @PathVariable LocalDate showsDate) {
        List<Shows> shows = showsService.getShowsByTheaterMovieAndDate(theatreId, movieId, showsDate);
        return ResponseEntity.ok(shows);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/seatSelection")
    public List<Seat> getDetail() {
        return seatService.getAllSeat();
    }

    // post mapping

    @PostMapping("/add/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    @PostMapping("/add/theatre")
    public ResponseEntity<Theatre> addMovie(@RequestBody Theatre theatre) {
        Theatre savedTheatre = theatreRepository.save(theatre);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTheatre);
    }

    @PostMapping("/add/shows")
    public ResponseEntity<?> createShow(@RequestBody Shows show) {
        Shows createdShow = showsService.createShow(show);
        return ResponseEntity.ok(createdShow);
    }

    @PostMapping("/addShows")
    public Shows addShows(@RequestBody Shows shows){
        System.out.println(shows);
        return showsRepository.save(shows);
    }




    @CrossOrigin(origins = "*")
    @PostMapping("/add/Booking")
    public Seat addSeat(@RequestBody Seat seat) {
        return seatService.addSeats(seat);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addbooking")
    public ResponseEntity<List<Booking>> createBookings(@RequestBody List<BookingRequest> bookingRequests) {
        List<Booking> createdBookings = bookingService.createBookings(bookingRequests);
        return new ResponseEntity<>(createdBookings, HttpStatus.CREATED);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("/seats/{showId}")
    public ResponseEntity<List<Integer>> getSeatNumbersByShowId(@PathVariable Long showId) {
        List<Integer> seatNumbers = bookingRepository.findSeatNumbersByShows(showId);
        return ResponseEntity.ok(seatNumbers);
    }



    @PutMapping("/{movieId}/with/{theaterId}")
    @CrossOrigin(origins = "*")
    public Movie addWithTheater(@PathVariable Long movieId, @PathVariable Long theaterId){
        return movieService.assignMovietoTheaters(movieId,theaterId);
    }

    @GetMapping("/count/{showsId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Integer> getBookingCountByShowsId(@PathVariable Long showsId) {
        int bookingCount = bookingService.getBookingCountByShowsId(showsId);
        return ResponseEntity.ok(bookingCount);
    }

    @GetMapping("/getMovie")
    @CrossOrigin(origins = "*")
    public List<Movie> findMovie(){
        return movieRepository.findAll();
    }

    @GetMapping("/getMovie/{id}")
    @CrossOrigin(origins = "*")
    public Optional<Movie> getMovie(@PathVariable Long id){
        return movieRepository.findById(id);
    }

    @PutMapping("/updateMovie")
    @CrossOrigin(origins = "*")
    public Movie updateMovie(@RequestBody Movie movie){
        return movieRepository.save(movie);
    }


    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Movie> getMovieBy(@PathVariable Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/moviess/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



//    @GetMapping("/movies/{id}")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<byte[]> getMovieImage(@PathVariable Long id) {
//        Movie movie = movieRepository.findById(id).orElse(null);
//
//        if (movie == null || movie.getImage() == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Set the appropriate content type for the image
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG); // Change this based on your image format
//
//        return new ResponseEntity<>(movie.getImage(), headers, HttpStatus.OK);
//    }

//    @PostMapping("/imageUpload")
//    public ResponseEntity<String> handleImageUpload(@RequestParam("file") MultipartFile file) {
//        // Check if the file is not empty
//        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("Please select an image file.");
//        }
//
//        try {
//            byte[] imageData = file.getBytes();
//            // Save the image bytes to the database
//            Movie movie = new Movie();
//            movie.setImage(imageData);
//            movieRepository.save(movie);
//
//            return ResponseEntity.ok("Image uploaded successfully!");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image: " + e.getMessage());
//        }
//    }
//    @GetMapping("/getMovie/{id}")
//    public ResponseEntity<byte[]> getMovieImage(@PathVariable Long id) {
//        Movie movie = movieRepository.findById(id).orElse(null);
//
//        if (movie == null || movie.getImage() == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Set the appropriate content type for the image
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG); // Change this based on your image format
//
//        return new ResponseEntity<>(movie.getImage(), headers, HttpStatus.OK);
//    }

    @PostMapping("/movieImageupload")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save the image to a folder on the server
            // You can use any storage service like AWS S3 or Google Cloud Storage for production applications.
            String uploadDir = "C:/mypc/react/moviebooking/public/images";
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the image information to the database
            Movie image = new Movie();
//          image.setTitle(file.getOriginalFilename());
//            image.setTitle(fileName.toString());
            image.setImage(fileName.toString());
            movieRepository.save(image);

            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }




    @GetMapping("/movieDisplay/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        Optional<Movie> imageOptional = movieRepository.findById(id);
        if (imageOptional.isPresent()) {
           Movie image = imageOptional.get();
            // Here, you can return the image path to display it on the frontend.
            return ResponseEntity.ok(image.getImage());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/movieDisplay/")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<?> getAllImage(@PathVariable Long id) {
//        Optional<Movie> imageOptional = movieRepository.findById(id);
//        if (imageOptional.isPresent()) {
//            Movie image = imageOptional.get();
//            // Here, you can return the image path to display it on the frontend.
//            return ResponseEntity.ok(image.getImage());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


    @GetMapping("/byUserAndShows")
    @CrossOrigin(origins = "*")
    public List<Booking> getBookingsByUserAndShows(
            @RequestParam(name = "userId") Long userId,
            @RequestParam(name = "showsId") Long showsId
    ) {
        // Retrieve User and Shows objects based on their IDs
        User user = bookingService.getUserById(userId);
        Shows shows = bookingService.getShowsById(showsId);

        // Call the service method to fetch bookings
        return bookingService.getBookingsByUserAndShows(user, shows);
    }


}





