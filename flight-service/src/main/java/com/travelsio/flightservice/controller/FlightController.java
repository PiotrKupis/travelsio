package com.travelsio.flightservice.controller;

import com.travelsio.flightservice.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final List<Flight> flights = Arrays.asList(new Flight(1L, "Spain"),
            new Flight(2L, "Egypt"));

    @GetMapping
    public List<Flight> getFlights() {
        return flights;
    }

    @GetMapping("/{flightId}")
    public Flight getFlight(@PathVariable("flightId") Long flightId) {
        return flights.stream()
                .filter(flight -> flight.getId().equals(flightId))
                .findFirst().orElse(null);
    }
}
