package com.movieticket.services;

import com.movieticket.dao.BookingRepository;
import com.movieticket.dao.ShowsRepository;
import com.movieticket.dao.UserRepository;
import com.movieticket.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowsRepository showsRepository;

//    public List<Booking> createBookings(List<Booking> bookings) {
//        List<Booking> createdBookings = new ArrayList<>();
//
//        for (Booking booking : bookings) {
//            // Perform any additional operations or validations before saving the booking
//
//            // Save the booking
//            Booking createdBooking = bookingRepository.save(booking);
//            createdBookings.add(createdBooking);
//        }
//
//        return createdBookings;
//    }

    public Booking createBooking(Long showId, Long userId, int seatNumber) {
        // Retrieve the Show and User entities from the repositories

        Shows show = showsRepository.findById(showId)
                .orElseThrow(() -> new NoSuchElementException("Show not found with ID: " + showId));


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        // Create a new Booking entity
        Booking booking = new Booking();
        booking.setShows(show);
        booking.setUser(user);
        booking.setSeatNumber(seatNumber);
        // Set any other properties of the booking if needed

        // Save the booking entity in the database
        Booking createdBooking = bookingRepository.save(booking);

        return createdBooking;
    }

    public List<Booking> createBookings(List<BookingRequest> bookingRequests) {
        List<Booking> createdBookings = new ArrayList<>();

        for (BookingRequest bookingRequest : bookingRequests) {
            Long showId = bookingRequest.getShowId();
            Long userId = bookingRequest.getUserId();
            int seatNumber = bookingRequest.getSeatNumber();

            // Retrieve the Show and User entities from the repositories
            Shows show = showsRepository.findById(showId)
                    .orElseThrow(() -> new NoSuchElementException("Show not found with ID: " + showId));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

            // Create a new Booking entity
            Booking booking = new Booking();
            booking.setShows(show);
            booking.setUser(user);
            booking.setSeatNumber(seatNumber);
            // Set any other properties of the booking if needed

            // Save the booking entity in the database
            Booking createdBooking = bookingRepository.save(booking);
            createdBookings.add(createdBooking);
        }
        return createdBookings;
    }



    public void deleteBookingById(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    public int getBookingCountByShowsId(Long showsId) {
        Shows shows = new Shows();
        shows.setId(showsId);
        return bookingRepository.countByShows(shows);
    }

    public List<Booking> getBookingsByUserAndShows(User user, Shows shows) {
        return bookingRepository.findByUserAndShows(user, shows);
    }

    public Shows getShowsById(Long showsId) {
        Optional<Shows> showsOptional = showsRepository.findById(showsId);
        return showsOptional.orElse(null); // Return null if not found
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null); // Return null if not found
    }
}
