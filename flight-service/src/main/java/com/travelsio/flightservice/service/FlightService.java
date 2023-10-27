package com.travelsio.flightservice.service;

import com.travelsio.flightservice.model.Offer;
import com.travelsio.flightservice.parser.FlightOffersParser;
import com.travelsio.flightservice.parser.HtmlParser;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final HtmlParser htmlParser;
    private final FlightOffersParser flightOffersParser;

    public List<Offer> getAvailableFlights(String departureAP, String arrivalAP) {

        String url = UriComponentsBuilder
            .fromUriString("https://www.skyscanner.pl/transport/loty")
            .path("/" + departureAP)
            .path("/" + arrivalAP)
            .path("/231214")
            .path("/231221")
            .queryParam("adultsv2", "2")
            .queryParam("stops", "!oneStop,!twoPlusStops")
            .build()
            .toUriString();

        Document document = htmlParser.parse(url);
        return flightOffersParser.parse(document);
    }
}
