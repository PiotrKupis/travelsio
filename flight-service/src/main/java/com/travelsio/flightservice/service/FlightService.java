package com.travelsio.flightservice.service;

import com.travelsio.flightservice.model.Offer;
import com.travelsio.flightservice.parser.FlightOffersParser;
import com.travelsio.flightservice.parser.HtmlParser;
import java.time.LocalDate;
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

    public List<Offer> getFlightOffers(String departureAP, String returnAP, Integer adults,
        LocalDate departureDate, LocalDate arrivalDate) {

        String departure = formatDateForApi(departureDate);
        String arrival = formatDateForApi(arrivalDate);

        String url = UriComponentsBuilder
            .fromUriString("https://www.skyscanner.pl/transport/loty")
            .path("/" + departureAP)
            .path("/" + returnAP)
            .path("/" + departure)
            .path("/" + arrival)
            .queryParam("adultsv2", adults)
            .queryParam("stops", "!oneStop,!twoPlusStops")
            .build()
            .toUriString();

        Document document = htmlParser.parse(url);
        return flightOffersParser.parse(document);
    }

    private static String formatDateForApi(LocalDate date) {
        return String.format("%02d%02d%02d", date.getYear() % 100, date.getMonth().getValue(),
            date.getDayOfMonth());
    }
}
