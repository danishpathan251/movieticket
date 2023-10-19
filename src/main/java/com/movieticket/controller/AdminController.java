package com.movieticket.controller;

import com.movieticket.dao.*;
import com.movieticket.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    public TheatreRepository theatreRepository;

    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    public ShowsRepository showsRepository;

    @Autowired
    public MovieRepository movieRepository;

    @Autowired
    public VoucherRepository voucherRepository;

    @Autowired
    public ArticleRepository articleRepository;

    @PostMapping("/addtheatre")
    public Theatre addTheatre(@RequestBody Theatre theatre) {
        return theatreRepository.save(theatre);
    }



    @GetMapping("/gettheatre")
    public List<Theatre> getTheate() {
        return theatreRepository.findAll();
    }

    @GetMapping("/gettheatre/:id")
    public Optional<Theatre> getTheatrebyid(@PathVariable Long id) {
        return theatreRepository.findById(id);

    }
    @GetMapping("/Vouchers")
    @CrossOrigin(origins = "*")
    public List<Voucher> getVoucher(){
        return voucherRepository.findAll();
    }

    @GetMapping("/Vouchers/{code}")
    @CrossOrigin(origins = "*")

    public ResponseEntity<?> getVoucherByCode(@PathVariable String code) {
        Optional<Voucher> OptionalVoucher = voucherRepository.findByCode(code);

        if (OptionalVoucher.isPresent()) {
            Voucher voucher = OptionalVoucher.get();
            return ResponseEntity.ok(voucher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/shows")
    @CrossOrigin(origins = "*")
    public Shows addshows(@RequestBody Shows shows) {
        return showsRepository.save(shows);
    }

    @GetMapping("/getshows")
    @CrossOrigin(origins = "*")
    public List<Shows> getshows() {
        return showsRepository.findAll();
    }

    @PostMapping("/addmovie")
    @CrossOrigin(origins = "*")
    public Movie addmovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @PostMapping("/addVoucher")
    @CrossOrigin(origins = "*")
    public Voucher addVoucher(@RequestBody Voucher voucher){
        return voucherRepository.save(voucher);
    }

    @PutMapping("/updateCodeImage/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {

            Optional<Voucher> voucherOptional = voucherRepository.findById(id);
            // Save the image to a folder on the server
            // You can use any storage service like AWS S3 or Google Cloud Storage for production applications.
            String uploadDir = "C:/mypc/react/moviebooking/public/codes";
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the image information to the database
            Voucher voucher = voucherOptional.get();

            voucher.setImage(fileName.toString());

            voucherRepository.save(voucher);

            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }

    @PutMapping("/updateMovieImage/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateMovieImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {

            Optional<Movie> MovieOptional = movieRepository.findById(id);

//            System.out.println(MovieOptional);
            String uploadDir = "C:/mypc/react/moviebooking/public/images";
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the image information to the database
            Movie movie = MovieOptional.get();

            movie.setImage(fileName.toString());

            movieRepository.save(movie);

            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }

    @PutMapping("/updateArticleImage/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateArticleImage(@PathVariable Long id, @RequestParam("image") MultipartFile image) {
        try {

            Optional<Articles> ArticleOptional = articleRepository.findById(id);

//            System.out.println(MovieOptional);
            String uploadDir = "C:/mypc/react/moviebooking/public/Articles";
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Save the image information to the database
            Articles articles = ArticleOptional.get();

            articles.setImage(fileName.toString());

           articleRepository.save(articles);

            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
        }
    }


    @PostMapping("/Showsadd")
    public ResponseEntity<showsDTO> addShows(@RequestBody showsDTO showsDTO){
        System.out.println(showsDTO);
        return ResponseEntity.ok(showsDTO);
    }

    @PostMapping("/addArticles")
    @CrossOrigin(origins = "*")
    public Articles addArticles(@RequestBody Articles articles){
        return articleRepository.save(articles);
    }

    @GetMapping("/Articles")
    @CrossOrigin(origins = "*")
    public List<Articles> getAllArticles(){
        return articleRepository.findAll();
    }
}
