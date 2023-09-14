package com.travelsio.hotelservice.controller;

import com.travelsio.hotelservice.model.Flight;
import com.travelsio.hotelservice.model.Hotel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final List<Hotel> hotels = Arrays.asList(
            new Hotel(1L, "Hotel1"),
            new Hotel(1L, "Hotel2"));

    @GetMapping
    public List<Hotel> getHotels() {
        return hotels;
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotel(@PathVariable("hotelId") Long hotelId) {
        return hotels.stream()
                .filter(hotel -> hotel.getId().equals(hotelId))
                .findFirst().orElse(null);
    }

    @GetMapping("/flight")
    public Flight[] getFlights() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Flight[]> forEntity = restTemplate.getForEntity("http://localhost:8080/flight-service/flights", Flight[].class);
        return forEntity.getBody();
    }
}
