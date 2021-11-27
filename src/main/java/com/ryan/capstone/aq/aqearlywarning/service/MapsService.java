package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.maps.GeocodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MapsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WebClient client = WebClient.create("https://maps.googleapis.com/maps/api");

    @Value("${googleMapsKey}")
    private String googleMapsKey;

    public Mono<GeocodeResponse> geocode(double latitude, double longitude) {
        logger.info("geocoding coordinates to address: " + latitude + " longitude: " + longitude);
        return client.get().uri("/geocode/json?latlng={latitude}, {longitude}&key={googleMapsKey}",
                        latitude, longitude, googleMapsKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(GeocodeResponse.class);
    }
}
