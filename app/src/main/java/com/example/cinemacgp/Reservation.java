package com.example.cinemacgp;

public class Reservation {
    private String movieTitle, customerName, cinema, reservationDate, movieImg, room;

    public Reservation(String movieTitle, String customerName, String cinema, String reservationDate, String movieImg, String room) {
        this.movieTitle = movieTitle;
        this.customerName = customerName;
        this.cinema = cinema;
        this.reservationDate = reservationDate;
        this.movieImg = movieImg;
        this.room = room;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(String movieImg) {
        this.movieImg = movieImg;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
