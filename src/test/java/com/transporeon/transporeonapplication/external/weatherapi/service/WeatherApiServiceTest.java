package com.transporeon.transporeonapplication.external.weatherapi.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class WeatherApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherApiService weatherApiService;

    // TO-DO: Mock @Value object
    /*
    @Test
    void getWeatherReturnWeatherApiForCityName() {
        final String cityName = "London";

        final City city = new City();
        city.setName(cityName);
        final WeatherApi weatherApi = new WeatherApi();
        weatherApi.setCity(city);


        Mockito.when(restTemplate.getForObject(anyString(), eq(WeatherApi.class)))
                .thenReturn(weatherApi);

        final WeatherApi result = weatherApiService.getWeather(cityName);
        Assertions.assertThat(result).isNotNull();
    }
     */
}