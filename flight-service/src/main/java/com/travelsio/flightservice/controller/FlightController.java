package com.travelsio.flightservice.controller;

import com.travelsio.flightservice.model.Offer;
import com.travelsio.flightservice.service.FlightService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/{departureAP}/{arrivalAP}")
    public List<Offer> getAvailableFlightOffers(@PathVariable("departureAP") String departureAP,
        @PathVariable("arrivalAP") String arrivalAP) {

        return flightService.getAvailableFlights(departureAP, arrivalAP);
    }
}
