package com.travelsio.flightservice.controller;

import com.travelsio.flightservice.model.Offer;
import com.travelsio.flightservice.service.FlightService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/{departureAP}/{arrivalAP}/{adults}/{departure}/{arrival}")
    public ResponseEntity<List<Offer>> getFlightOffers(
        @PathVariable("departureAP") String departureAP,
        @PathVariable("arrivalAP") String arrivalAP,
        @PathVariable("adults") Integer adults,
        @PathVariable("departure") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate departure,
        @PathVariable("arrival") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate arrival) {

        List<Offer> offers = flightService.getFlightOffers(departureAP, arrivalAP, adults,
            departure, arrival);
        return ResponseEntity.ok(offers);
    }
}
