package com.travelsio.hotelservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelsio.hotelservice.configuration.BookingApiClient;
import com.travelsio.hotelservice.entity.Location;
import com.travelsio.hotelservice.mapper.LocationMapper;
import com.travelsio.hotelservice.repository.LocationRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationService {

    private static final Logger LOGGER = LogManager.getLogger(LocationService.class);

    private final LocationRepository locationRepository;
    private final BookingApiClient bookingApiClient;

    public Long getLocation(String searchedLocation) {

        Location foundLocation = locationRepository.findMostPopularByLabel(searchedLocation)
                .orElseGet(() -> getMostPopularNewLocation(searchedLocation));
        return foundLocation.getDestinationId();
    }

    private Location getMostPopularNewLocation(String searchedLocation) {
        List<Location> foundLocations = fetchNewLocations(searchedLocation);
        foundLocations.sort(Comparator.comparingInt(Location::getHotelsQuantity).reversed());
        return foundLocations.get(0);
    }

    private List<Location> fetchNewLocations(String searchedLocation) {

        List<Location> foundLocations = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        String json = bookingApiClient.get(String.class,
            "locations/auto-complete?text=" + searchedLocation).getBody();

        try {
            List<Map<String, Object>> locationsJson =
                objectMapper.readValue(json, new TypeReference<>() {});

            for (Map<String, Object> foundLocation : locationsJson) {
                Location newLocation = LocationMapper.fromBookingApi(foundLocation);
                foundLocations.add(newLocation);
            }

            locationRepository.saveAll(foundLocations);
        } catch (JsonProcessingException e) {
            //TODO add custom exceptions
            LOGGER.error("Error during fetching new locations for = '{}'",
                searchedLocation, e);
            String message = String.format("Error during fetching new locations for = '%s'",
                searchedLocation);
            throw new RuntimeException(message);
        }

        return foundLocations;
    }
}
