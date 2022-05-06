package com.transporeon.transporeonapplication.external.weatherapi.service;

import com.transporeon.transporeonapplication.external.weatherapi.model.WeatherApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherApiService {

    private final RestTemplate restTemplate;

    @Value("${weather_api_key}")
    private String apiKey;
    @Value("${weather_api_url_city}")
    private String weatherApiUrlCity;

    public WeatherApi getWeather(final String cityName) {
        final String url = String.format(weatherApiUrlCity, cityName);
        return restTemplate.getForObject(url, WeatherApi.class);
    }
}
