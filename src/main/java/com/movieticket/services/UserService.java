package com.movieticket.services;

import com.movieticket.dao.UserRepository;
import com.movieticket.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public UserRepository userRepository;


    public List<User> getAllDetail(){
        return userRepository.findAll();

    }

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }


    public Optional<User> getUserDetail(String username){
        return userRepository.findByUsername(username);
    }


    public User updateUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    public void updatePassword(Long userId, String password) {
        // Find the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + userId));

        // Update the user's password
        user.setPassword(passwordEncoder.encode(password));

        // Save the updated user
        userRepository.save(user);
    }

    public void updateMobile(Long id, String mnumber) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (mnumber != null) {
                user.setMnumber(mnumber);
            }

            userRepository.save(user);


        } else {
            // Handle the case where the user with the given ID is not found
        }
    }

    public boolean emailExists(String email) {

        return userRepository.existsByEmail(email);
    }
}
