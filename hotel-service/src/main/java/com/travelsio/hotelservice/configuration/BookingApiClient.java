package com.travelsio.hotelservice.configuration;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class BookingApiClient {

    private static final String DEFAULT_LANG_CODE = "pl";
    private final RestTemplate restTemplate;

    @Value("${rapid.api.key}")
    private String rapidApiKey;
    @Value("${rapid.api.host}")
    private String rapidApiHost;
    @Value("${rapid.api.path}")
    private String basicPath;

    public <T> ResponseEntity<T> get(Class<T> returnType, String path) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-RapidAPI-Key", rapidApiKey);
        headers.add("X-RapidAPI-Host", rapidApiHost);

        String url = UriComponentsBuilder
            .fromUriString(basicPath + path)
            .queryParam("languagecode", DEFAULT_LANG_CODE)
            .build()
            .toUriString();

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET,
            URI.create(url));
        return restTemplate.exchange(requestEntity, returnType);
    }
}
