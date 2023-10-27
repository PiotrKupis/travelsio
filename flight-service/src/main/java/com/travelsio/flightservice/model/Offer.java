package com.travelsio.flightservice.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer {

    private String url;
    private BigDecimal minTotalPrice;
    private Flight destinationFlight;
    private Flight returnFlight;
}
