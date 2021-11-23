package com.ryan.capstone.aq.aqearlywarning.service;

import com.ryan.capstone.aq.aqearlywarning.domain.dto.PollutionStatusDTO;
import com.ryan.capstone.aq.aqearlywarning.domain.openweather.PollutionListItem;
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

    public Mono<PollutionResponse> getPollutionForecast(double latitude, double longitude) {
        logger.info("fetching pollution forecast at location latitude: " + latitude + " longitude: " + longitude);

        return client.get()
                .uri("/air_pollution/forecast?lat={latitude}&lon={longitude}&appid={openWeatherAppId}",
                        latitude, longitude, openWeatherAppId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PollutionResponse.class)
                .map(pollutionResponse -> {
                    var list = pollutionResponse.getList();

                    for (PollutionListItem pollutionListItem : list) {
                        pollutionListItem.setAqi(pm25ToAQI(pollutionListItem.getComponents().getPm2_5()));
                    }
                    return pollutionResponse;
                });
    }

    private int pm25ToAQI(float pm) {
        if (pm < 0) return (int) pm;

        if (pm > 350.5) {
            return calcAQI(pm, 500, 401, 500, 350.5);
        } else if (pm > 250.5) {
            return calcAQI(pm, 400, 301, 350.4, 250.5);
        } else if (pm > 150.5) {
            return calcAQI(pm, 300, 201, 250.4, 150.5);
        } else if (pm > 55.5) {
            return calcAQI(pm, 200, 151, 150.4, 55.5);
        } else if (pm > 35.5) {
            return calcAQI(pm, 150, 101, 55.4, 35.5);
        } else if (pm > 12.1) {
            return calcAQI(pm, 100, 51, 35.4, 12.1);
        } else {
            return calcAQI(pm, 50, 0, 12, 0);
        }
    }

    private int calcAQI(double Cp, double Ih, double Il, double BPh, double BPl) {
        var a = (Ih - Il);
        var b = (BPh - BPl);
        var c = (Cp - BPl);
        return (int) Math.round((a / b) * c + Il);
    }

    private PollutionStatusDTO convertToPollutionStatusDTO(PollutionResponse pollutionResponse) {
        logger.info("pollution response received, converting to pollutionDTO " + pollutionResponse);
        PollutionStatusDTO pollutionStatus = new PollutionStatusDTO();

        pollutionStatus.setAqi(pm25ToAQI(pollutionResponse.getList().get(0).getComponents().getPm2_5()));
        pollutionStatus.setDate(pollutionResponse.getList().get(0).getDt());
        pollutionStatus.setLatitude(pollutionResponse.getLatitude());
        pollutionStatus.setLongitude(pollutionResponse.getLongitude());
        pollutionStatus.setAqiComponents(pollutionResponse.getList().get(0).getComponents());

        return pollutionStatus;
    }
}
