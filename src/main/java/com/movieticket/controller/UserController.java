package com.movieticket.controller;

import com.movieticket.dao.BookingRepository;
import com.movieticket.dao.ShowsRepository;
import com.movieticket.dao.UserRepository;
import com.movieticket.entities.*;
import com.movieticket.services.BookingService;
import com.movieticket.services.JwtService;
import com.movieticket.services.ShowsService;
import com.movieticket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public UserService userService;

    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ShowsRepository showsRepository;

    @Autowired
    private ShowsService showsService;

    @GetMapping("/showslist")
    public List<Shows> GetAllshows(){
        return showsRepository.findAll();
    }
    @GetMapping("/Userinfo")
    @CrossOrigin(origins = "*")
    public List<User> getDetails(){
        return userService.getAllDetail();
    }


    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody User user) {
        String email = user.getEmail();

        // Check if the email is already registered in your database
        if (userService.emailExists(email)) {
            return ResponseEntity.badRequest().body("Email already exists");
        } else {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/authentication")
    public String authenticationAndGetToken(@RequestBody User user){

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());

        }
        else{
            throw new UsernameNotFoundException("invalid User");
        }

    }



    @GetMapping("getuser/{username}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUser(@PathVariable String username){

        Optional<User> userinfo =userService.getUserDetail(username);

        if(userinfo.isPresent()){
            return ResponseEntity.ok(userinfo.get());
        }
        else{
                return ResponseEntity.notFound().build();
        }
    }


    @CrossOrigin(origins="*")
    @GetMapping("/users/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return bookingRepository.findByUser(user);
    }
    @CrossOrigin(origins="*")
    @GetMapping("/shows/{showsId}")
    public List<Booking> getBookingsByShows(@PathVariable Long showsId) {
        Shows shows = new Shows();
        shows.setId(showsId);
        return bookingRepository.findByShows(shows);
    }
//    public String getMessage(@PathVariable){
//    }

    @CrossOrigin(origins="*")
    @GetMapping("/show/{showId}")
    public ResponseEntity<Shows> getShowById(@PathVariable Long showId) {
        // Logic to fetch the show by ID
        Shows show = showsService.getShowById(showId);

        if (show != null) {
            return ResponseEntity.ok(show);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/totalamount/{userid}")
    public List<Booking> getAmountByShows(@PathVariable Long userid) {
        User user = new User();
        user.setId(userid);
        return bookingRepository.findByUser(user);
    }

    @DeleteMapping("/delete/{bookingId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteBookingById(@PathVariable Long bookingId) {
        bookingService.deleteBookingById(bookingId);
        return ResponseEntity.ok("Booking deleted successfully");
    }


    @PutMapping("/update")
    @CrossOrigin(origins = "*")
    public User updateUser(@RequestBody User user){
//        Optional<User> user1 = userRepository.findById(userid);
        return userService.updateUser(user);
    }


    @GetMapping("/check-email/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> checkEmailExistence(@PathVariable String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user); // Return the User object
        } else {
            return ResponseEntity.notFound().build(); // Return a 404 Not Found status if email doesn't exist
        }
    }


    @PutMapping("/{userId}/update-password")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updatePassword(
            @PathVariable Long userId,
            @RequestBody String password
    ) {
        // Check authentication and authorization here...

        userService.updatePassword(userId, password);
        return ResponseEntity.ok("Password updated successfully");
    }

    @PutMapping("update/Mnumber/{id}")
    public ResponseEntity<String> updateNameById(
            @PathVariable Long id,
            @RequestBody(required = false) String mnumber) {

        userService.updateMobile(id, mnumber);
        return ResponseEntity.ok("Name updated successfully");
    }

}


