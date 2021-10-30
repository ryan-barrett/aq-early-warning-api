package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.PollutionStatusDTO;
import com.ryan.capstone.aq.aqearlywarning.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    WeatherController(@Autowired WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/pollution")
    public Mono<PollutionStatusDTO> getPollution(@RequestParam("latitude") Float latitude,
                                                 @RequestParam("longitude") Float longitude) {
        return weatherService.getPollution(latitude, longitude);
    }
}
