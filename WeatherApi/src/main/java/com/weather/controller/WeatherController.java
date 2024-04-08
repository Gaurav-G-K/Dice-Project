package com.weather.controller;


import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weather.service.WeatherService;

@RestController
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather/forecast-summary")
    public ResponseEntity<String> getForecastSummary(@RequestParam String city) throws IOException, InterruptedException {
        System.out.println(city);
        return ResponseEntity.ok(weatherService.getForecastSummary(city));
    }

    @GetMapping("/weather/hourly-forecast")
    public ResponseEntity<String> getHourlyForecast(@RequestParam String city) {
        return ResponseEntity.ok(weatherService.getHourlyForecast(city));
    }
}