package com.travelsio.hotelservice.controller;

import com.travelsio.hotelservice.dto.Flight;
import com.travelsio.hotelservice.model.Hotel;
import com.travelsio.hotelservice.service.HotelService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/{destination}/{adults}/{checkin}/{checkout}")
    public ResponseEntity<List<Hotel>> findHotels(
        @PathVariable("destination") String destination,
        @PathVariable("adults") Integer adults,
        @PathVariable("checkin") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate checkin,
        @PathVariable("checkout") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate checkout) {
        List<Hotel> hotels = hotelService.findHotels(destination, adults, checkin, checkout);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/flight")
    public Flight[] getFlights() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Flight[]> forEntity = restTemplate.getForEntity(
            "http://localhost:8080/flight-service/flights", Flight[].class);
        return forEntity.getBody();
    }
}
