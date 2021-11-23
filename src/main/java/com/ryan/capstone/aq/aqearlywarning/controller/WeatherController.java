package com.ryan.capstone.aq.aqearlywarning.controller;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.PollutionStatusDTO;
import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionResponse;
import com.ryan.capstone.aq.aqearlywarning.service.AuthService;
import com.ryan.capstone.aq.aqearlywarning.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final AuthService authService;

    WeatherController(@Autowired AuthService authService, @Autowired WeatherService weatherService) {
        this.weatherService = weatherService;
        this.authService = authService;
    }

    @GetMapping("/pollution")
    public Mono<PollutionStatusDTO> getPollution(@RequestHeader("authorization") String token,
                                                 @RequestParam("latitude") double latitude,
                                                 @RequestParam("longitude") double longitude) throws AuthenticationException {
        authService.iosAuth(token);
        return weatherService.getPollution(latitude, longitude);
    }

    @GetMapping("/pollution/forecast")
    public Mono<PollutionResponse> getPollutionForecast(@RequestHeader("authorization") String token,
                                                        @RequestParam("latitude") double latitude,
                                                        @RequestParam("longitude") double longitude) throws AuthenticationException {
        authService.iosAuth(token);
        return weatherService.getPollutionForecast(latitude, longitude);
    }
}
