package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.PollutionStatusDTO;
import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionResponse;
import com.ryan.capstone.aq.aqearlywarning.service.AuthService;
import com.ryan.capstone.aq.aqearlywarning.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    WeatherController(@Autowired WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/pollution")
    public Mono<PollutionStatusDTO> getPollution(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        return weatherService.getPollution(latitude, longitude);
    }

    @GetMapping("/pollution/forecast")
    public Mono<PollutionResponse> getPollutionForecast(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        return weatherService.getPollutionForecast(latitude, longitude);
    }
}
