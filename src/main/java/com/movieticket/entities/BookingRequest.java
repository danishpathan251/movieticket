package com.movieticket.entities;

public class BookingRequest {

        private Long showId;
        private Long userId;
        private int seatNumber;

    public BookingRequest(Long showId, Long userId, int seatNumber) {
        this.showId = showId;
        this.userId = userId;
        this.seatNumber = seatNumber;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
