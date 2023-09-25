package com.travelsio.hotelservice.mapper;

import com.travelsio.hotelservice.entity.Location;
import java.util.Map;

public class LocationMapper {

    private static final String DEST_ID_KEY = "dest_id";
    private static final String COUNTRY_KEY = "country";
    private static final String REGION_KEY = "region";
    private static final String NAME_KEY = "name";
    private static final String LABEL_KEY = "label";
    private static final String TYPE_KEY = "dest_type";
    private static final String TIMEZONE_KEY = "timezone";
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    private static final String HOTELS_QUANTITY_KEY = "hotels";

    public static Location fromBookingApi(Map<String, Object> locationJson) {
        return Location.builder()
            .externalId(Long.parseLong((String) locationJson.get(DEST_ID_KEY)))
            .country((String) locationJson.get(COUNTRY_KEY))
            .region((String) locationJson.get(REGION_KEY))
            .name((String) locationJson.get(NAME_KEY))
            .label((String) locationJson.get(LABEL_KEY))
            .type((String) locationJson.get(TYPE_KEY))
            .timeZone((String) locationJson.get(TIMEZONE_KEY))
            .latitude((Double) locationJson.get(LATITUDE_KEY))
            .longitude((Double) locationJson.get(LONGITUDE_KEY))
            .hotelsQuantity((Integer) locationJson.get(HOTELS_QUANTITY_KEY))
            .build();
    }
}
