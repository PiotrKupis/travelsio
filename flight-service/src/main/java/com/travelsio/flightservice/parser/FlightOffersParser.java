package com.travelsio.flightservice.parser;

import com.travelsio.flightservice.model.Flight;
import com.travelsio.flightservice.model.Offer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class FlightOffersParser {

    private static final String SINGLE_OFFER_SELECTOR = "div[class^=FlightsTicket_container]";
    private static final String URL_SELECTOR = "a[class^=FlightsTicket_link]";
    private static final String SINGLE_FLIGHT_SELECTOR = "div[class^=LegDetails_container]";
    private static final String TIME_SELECTOR = "span[class*=LegInfo_routePartialTime]";
    private static final String AIRPORT_SELECTOR = "span[class*=LegInfo_routePartialCityTooltip]";
    private static final String DURATION_SELECTOR = "span[class*=Duration_duration]";
    private static final String TOTAL_PRICE_SELECTOR = "span[class*=Price_totalPrice]";;

    public List<Offer> parse(Document document) {

        List<Offer> offers = new ArrayList<>();
        Elements offersEl = document.select(SINGLE_OFFER_SELECTOR);

        for (Element offerEl : offersEl) {

            Element urlEl = offerEl.select(URL_SELECTOR).first();
            Element totalPriceEl = offerEl.select(TOTAL_PRICE_SELECTOR).first();

            Elements flightsEl = offerEl.select(SINGLE_FLIGHT_SELECTOR);
            Element destinationFlightEl = flightsEl.first();
            Element returnFlightEl = flightsEl.last();

            Flight destinationFlight = parseFlight(destinationFlightEl);
            Flight returnFlight = parseFlight(returnFlightEl);

            if (destinationFlight == null || returnFlight == null || urlEl == null ||
                totalPriceEl == null) {
                continue;
            }

            String offerUrl = formatOfferUrl(urlEl);
            BigDecimal minTotalPrice = formatMinTotalPrice(totalPriceEl);

            Offer offer = new Offer(offerUrl, minTotalPrice, destinationFlight, returnFlight);
            offers.add(offer);
        }

        return offers;
    }

    private static String formatOfferUrl(Element urlEl) {
        String offerUrl = urlEl.attr("href");
        return "https://www.skyscanner.pl" + offerUrl;
    }

    private static BigDecimal formatMinTotalPrice(Element totalPriceEl) {
        String price = totalPriceEl.text().replace(" ", "");
        price = StringUtils.substringBeforeLast(price, "złrazem");
        return new BigDecimal(price);
    }

    private static Flight parseFlight(Element flightEl) {

        if (flightEl == null) {
            return null;
        }

        Element departureEl = flightEl.select(TIME_SELECTOR).first();
        Element departureAirportEl = flightEl.select(AIRPORT_SELECTOR).first();
        Element arrivalEl = flightEl.select(TIME_SELECTOR).last();
        Element arrivalAirportEl = flightEl.select(AIRPORT_SELECTOR).last();
        Element durationEl = flightEl.select(DURATION_SELECTOR).first();

        Flight flight = null;

        if (departureEl != null && departureAirportEl != null && arrivalEl != null &&
            arrivalAirportEl != null && durationEl != null) {

            String departure = departureEl.text();
            String departureAirport = departureAirportEl.text();
            String arrival = arrivalEl.text();
            String arrivalAirport = arrivalAirportEl.text();
            String duration = durationEl.text();

            flight = new Flight(departure, departureAirport, arrival, arrivalAirport, duration);
        }

        return flight;
    }
}
