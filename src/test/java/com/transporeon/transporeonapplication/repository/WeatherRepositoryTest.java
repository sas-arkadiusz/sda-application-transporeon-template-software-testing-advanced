package com.transporeon.transporeonapplication.repository;

import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // 1. Add that annotation to start database related things
class WeatherRepositoryTest {

    @Autowired // 2. Create a field for the repository
    private WeatherRepository weatherRepository;

    @Test
    void findByCityNameAndDateShouldReturnWeatherEntity() {
        double temp = 15;
        String cityName = "London";

        saveWeatherWithCityNameAndTempToDatabase(cityName, temp);

        WeatherEntity weatherFoundByCityNameAndDate = weatherRepository.findByCityNameAndTemp(cityName, temp);
        Assertions.assertThat(weatherFoundByCityNameAndDate.getCityName()).isEqualTo(cityName);
        Assertions.assertThat(weatherFoundByCityNameAndDate.getTemp()).isEqualTo(temp);
    }

    @Test
    void findByHighestTempShouldReturnWeatherWithHighestTemp() {
        saveWeatherWithCityNameAndTempToDatabase("Warsaw", 10.07);
        saveWeatherWithCityNameAndTempToDatabase("Cracow", 12.37);

        final WeatherEntity weatherWithHighestTemp = weatherRepository.findByHighestTemp();
        Assertions.assertThat(weatherWithHighestTemp.getCityName()).isEqualTo("Cracow");
    }

    private WeatherEntity saveWeatherWithCityNameAndTempToDatabase(final String cityName, final double temp) {
        final WeatherEntity weather = new WeatherEntity();
        weather.setCityName(cityName);
        weather.setTemp(temp);

        return weatherRepository.save(weather);
    }
















}