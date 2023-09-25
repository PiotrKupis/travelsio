package com.travelsio.hotelservice.controller;

import com.travelsio.hotelservice.entity.Location;
import com.travelsio.hotelservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping("{location}")
    public ResponseEntity<Long> getLocation(@PathVariable("location") String searchedLocation) {
        Location location = locationService.getLocation(searchedLocation);
        return ResponseEntity.ok(location.getExternalId());
    }
}
