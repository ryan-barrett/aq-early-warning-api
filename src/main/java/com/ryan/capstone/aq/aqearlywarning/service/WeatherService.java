package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.PollutionStatusDTO;
import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    private final WebClient client = WebClient.create("http://api.openweathermap.org/data/2.5");

    @Value("${openWeatherAppId}")
    private String openWeatherAppId;

    public Mono<PollutionStatusDTO> getPollution(Float latitude, Float longitude) {
        return client.get()
                .uri("/air_pollution?lat={latitude}&lon={longitude}&appid={openWeatherAppId}",
                        latitude, longitude, openWeatherAppId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PollutionResponse.class)
                .map(this::convertToPollutionStatusDTO);
    }

    private PollutionStatusDTO convertToPollutionStatusDTO(PollutionResponse pollutionResponse) {
        PollutionStatusDTO pollutionStatus = new PollutionStatusDTO();

        pollutionStatus.setAqi(pollutionResponse.getList().get(0).getAqi());
        pollutionStatus.setDate(pollutionResponse.getList().get(0).getDt());
        pollutionStatus.setLatitude(pollutionResponse.getLongitude());
        pollutionStatus.setLongitude(pollutionResponse.getLongitude());
        pollutionStatus.setAqiComponents(pollutionResponse.getList().get(0).getComponents());

        return pollutionStatus;
    }
}
