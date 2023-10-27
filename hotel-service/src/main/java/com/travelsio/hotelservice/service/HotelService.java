package com.travelsio.hotelservice.service;

import com.travelsio.hotelservice.entity.Location;
import com.travelsio.hotelservice.model.Hotel;
import com.travelsio.hotelservice.parser.HotelParser;
import com.travelsio.hotelservice.parser.HtmlParser;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final LocationService locationService;
    private final HtmlParser htmlParser;
    private final HotelParser hotelParser;

    public List<Hotel> findHotels(String destination, Integer adults, LocalDate checkin,
        LocalDate checkout) {

        Location location = locationService.getLocation(destination);

        String url = UriComponentsBuilder
            .fromUriString("https://www.booking.com/searchresults.pl.html")
            .queryParam("dest_id", location.getExternalId())
            .queryParam("dest_type", location.getType())
            .queryParam("group_adults", adults)
            .queryParam("order", "price")
            .queryParam("checkin_year", checkin.getYear())
            .queryParam("checkin_month", checkin.getMonth().getValue())
            .queryParam("checkin_monthday", checkin.getDayOfMonth())
            .queryParam("checkout_year", checkout.getYear())
            .queryParam("checkout_month", checkout.getMonth().getValue())
            .queryParam("checkout_monthday", checkout.getDayOfMonth())
            .build()
            .toUriString();

        Document document = htmlParser.parse(url);
        return hotelParser.parse(document);
    }
}
