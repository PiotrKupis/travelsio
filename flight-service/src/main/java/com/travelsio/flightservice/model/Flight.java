package com.travelsio.flightservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

    private String departure;
    private String departureAirport;
    private String arrival;
    private String arrivalAirport;
    private String duration;
}
