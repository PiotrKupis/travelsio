package com.travelsio.hotelservice.model;

public class Flight {

    private Long id;
    private String destination;

    public Flight() {
    }

    public Flight(Long id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
