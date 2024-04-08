package com.weather.service;

import com.weather.config.WeatherIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {

    @Autowired
    private WeatherIntegrationService weatherIntegrationService;

    public String getForecastSummary(String city) throws IOException, InterruptedException {
        return weatherIntegrationService.getForecastSummary(city);
    }

    public String getHourlyForecast(String city) {
        return weatherIntegrationService.getHourlyForecast(city);
    }
}