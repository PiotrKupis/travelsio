package com.travelsio.hotelservice.service;

import com.travelsio.hotelservice.configuration.BookingApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final BookingApiClient bookingApiClient;
}
