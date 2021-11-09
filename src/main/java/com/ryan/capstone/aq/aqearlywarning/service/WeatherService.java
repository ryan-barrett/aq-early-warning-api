package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.PollutionStatusDTO;
import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final WebClient client = WebClient.create("http://api.openweathermap.org/data/2.5");

    @Value("${openWeatherAppId}")
    private String openWeatherAppId;

    public Mono<PollutionStatusDTO> getPollution(double latitude, double longitude) {
        logger.info("fetching pollution info at location latitude: " + latitude + " longitude: " + longitude);
        return client.get()
                .uri("/air_pollution?lat={latitude}&lon={longitude}&appid={openWeatherAppId}",
                        latitude, longitude, openWeatherAppId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PollutionResponse.class)
                .map(this::convertToPollutionStatusDTO);
    }

    private PollutionStatusDTO convertToPollutionStatusDTO(PollutionResponse pollutionResponse) {
        logger.info("pollution response received, converting to pollutionDTO " + pollutionResponse);
        PollutionStatusDTO pollutionStatus = new PollutionStatusDTO();

        // TODO: don't do this
//        pollutionStatus.setAqi(pollutionResponse.getList().get(0).getAqi());
        pollutionStatus.setAqi(5);
        pollutionStatus.setDate(pollutionResponse.getList().get(0).getDt());
        pollutionStatus.setLatitude(pollutionResponse.getLatitude());
        pollutionStatus.setLongitude(pollutionResponse.getLongitude());
        pollutionStatus.setAqiComponents(pollutionResponse.getList().get(0).getComponents());

        return pollutionStatus;
    }
}
