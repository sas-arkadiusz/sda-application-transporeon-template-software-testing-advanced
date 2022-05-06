package com.transporeon.transporeonapplication.service;

import com.transporeon.transporeonapplication.exception.WeatherAlreadyExistsException;
import com.transporeon.transporeonapplication.external.weatherapi.model.City;
import com.transporeon.transporeonapplication.external.weatherapi.model.ListItem;
import com.transporeon.transporeonapplication.external.weatherapi.model.WeatherApi;
import com.transporeon.transporeonapplication.external.weatherapi.service.WeatherApiService;
import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import com.transporeon.transporeonapplication.model.request.WeatherRequestWithCityName;
import com.transporeon.transporeonapplication.repository.WeatherRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

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

    @Test
    void addWeatherThrowsExceptionWhenWeatherAlreadyExists() {
        final String cityName = "Warsaw";
        final Timestamp date = Timestamp.from(Instant.now());
        final WeatherRequestWithCityName request = WeatherRequestWithCityName.of(cityName, date);

        Mockito.when(weatherRepository.findByCityNameAndDate(Mockito.anyString(), Mockito.any(Timestamp.class)))
                .thenReturn(new WeatherEntity());

        Assertions.assertThatThrownBy(() -> weatherService.addWeather(request))
                .isInstanceOf(WeatherAlreadyExistsException.class)
                .hasMessage("Weather for the given city or coordinates already exists!");

        Mockito.verify(weatherRepository).findByCityNameAndDate(cityName, date);
        // Mockito.verify(weatherRepository, Mockito.times(2)).findByCityNameAndDate(cityName, date);
        Mockito.verify(weatherApiService, Mockito.never()).getWeather(cityName);
    }

    @Test
    void addWeatherDoesNotThrowExceptionWhenWeatherDoesNotExistInDatabase() throws WeatherAlreadyExistsException {
        final String cityName = "Warsaw";
        final Timestamp date = Timestamp.from(Instant.now());
        final WeatherRequestWithCityName request = WeatherRequestWithCityName.of(cityName, date);

        final ListItem listItem = new ListItem();
        listItem.setDt(date.getTime());
        final List<ListItem> list = List.of(listItem);

        final City city = new City();
        city.setName(cityName);

        final WeatherApi weatherApi = new WeatherApi();
        weatherApi.setCity(city);
        weatherApi.setList(list);

        final WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCityName(cityName);
        weatherEntity.setDate(date);

        Mockito.when(weatherRepository.findByCityNameAndDate(Mockito.anyString(), Mockito.any(Timestamp.class)))
                .thenReturn(null);
        Mockito.when(weatherApiService.getWeather(anyString()))
                .thenReturn(weatherApi);
        Mockito.when(weatherRepository.saveAll(anyList()))
                .thenReturn(List.of(weatherEntity));

        weatherService.addWeather(request);

        Mockito.verify(weatherRepository).findByCityNameAndDate(cityName, date);
        Mockito.verify(weatherApiService).getWeather(cityName);
    }
}