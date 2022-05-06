package com.transporeon.transporeonapplication.external.weatherapi.service;

import com.transporeon.transporeonapplication.external.weatherapi.model.WeatherApi;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class WeatherApiServiceIntegrationTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherApiService weatherApiService;

    @Test
    void getWeatherReturnWeatherApiForCityName() {
        final String cityName = "London";

        final WeatherApi result = weatherApiService.getWeather(cityName);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getCity().getName()).isEqualTo(cityName);
    }

}