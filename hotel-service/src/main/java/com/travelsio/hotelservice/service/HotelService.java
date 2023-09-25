package com.travelsio.hotelservice.service;

import com.travelsio.hotelservice.Parser.HotelParser;
import com.travelsio.hotelservice.Parser.HtmlParser;
import com.travelsio.hotelservice.entity.Location;
import com.travelsio.hotelservice.model.Hotel;
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

    public List<Hotel> getHotelsByLocation(String searchedLocation) {

        Location location = locationService.getLocation(searchedLocation);

        String url = UriComponentsBuilder
            .fromUriString("https://www.booking.com/searchresults.pl.html")
            .queryParam("dest_id", location.getExternalId())
            .queryParam("dest_type", location.getType())
            .queryParam("group_adults", "2")
            .queryParam("order", "price")
            .queryParam("checkin_year", "2023")
            .queryParam("checkin_month", "10")
            .queryParam("checkin_monthday", "12")
            .queryParam("checkout_year", "2023")
            .queryParam("checkout_month", "10")
            .queryParam("checkout_monthday", "17")
            .build()
            .toUriString();

        Document document = htmlParser.parse(url);
        return hotelParser.parse(document);
    }
}
