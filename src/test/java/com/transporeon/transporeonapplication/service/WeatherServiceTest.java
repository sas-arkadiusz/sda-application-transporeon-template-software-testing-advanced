package com.transporeon.transporeonapplication.service;

import com.transporeon.transporeonapplication.external.weatherapi.service.WeatherApiService;
import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import com.transporeon.transporeonapplication.repository.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class) // 1. Add MockitoExtension to integrate with JUnit
/*@ExtendWith(SpringExtension.class)*/
class WeatherServiceTest {

    // 2. Mock all the dependencies
    @Mock
    private WeatherApiService weatherApiService;

    @Mock
    private WeatherRepository weatherRepository;

    // 3. Inject created mocks to our service
    @InjectMocks
    private WeatherService weatherService;

    @Test
    void getAllWeatherRecordsReturnsListOfWeatherRecordsWhenDatabaseIsNotEmpty() {
        final WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCityName("Warsaw");

        Mockito.when(weatherRepository.findAll())
                .thenReturn(List.of(weatherEntity, new WeatherEntity()));

        final List<WeatherDto> resultWeatherRecords = weatherService.getAllWeatherRecords();

        Assertions.assertThat(resultWeatherRecords).hasSize(2);
        Assertions.assertThat(resultWeatherRecords.get(0).getCityName()).isEqualTo("Warsaw");
    }
}