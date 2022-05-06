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
        double temp = 10;
        String cityName = "Warsaw";

        WeatherEntity weather = new WeatherEntity();
        weather.setCityName(cityName);
        weather.setTemp(temp);

        weatherRepository.save(weather);

        WeatherEntity weatherFoundByCityNameAndDate = weatherRepository.findByCityNameAndTemp(cityName, temp);
        Assertions.assertThat(weatherFoundByCityNameAndDate.getCityName()).isEqualTo(cityName);
        Assertions.assertThat(weatherFoundByCityNameAndDate.getTemp()).isEqualTo(temp);
    }
}