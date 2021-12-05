package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.maps.GeocodeResponse;
import com.ryan.capstone.aq.aqearlywarning.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/maps")
public class MapsController {
    private final MapsService mapsService;

    MapsController(@Autowired MapsService mapsService) {
        this.mapsService = mapsService;
    }

    @GetMapping("/geocode")
    public Mono<GeocodeResponse> geocode(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        return mapsService.geocode(latitude, longitude);
    }

    @GetMapping("/address")
    public Mono<GeocodeResponse> addressSearch(@RequestParam("address") String address) {
        return mapsService.addressSearch(address);
    }
}
