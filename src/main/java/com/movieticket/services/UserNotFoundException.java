package com.movieticket.services;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String s) {

        super(s);
    }
}
