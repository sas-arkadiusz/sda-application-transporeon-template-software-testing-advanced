package com.transporeon.transporeonapplication.mapper;

import com.transporeon.transporeonapplication.factory.WeatherEntityTestFactory;
import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WeatherMapperTest {

    @Test
    void mapReturnsCorrectlyMappedWeatherEntityToWeatherDto() {
        final WeatherEntity weatherEntity = WeatherEntityTestFactory.buildValidWeatherEntity();

        final WeatherDto weatherDto = WeatherMapper.map(weatherEntity);

        Assertions.assertThat(weatherDto.getWeatherId()).isEqualTo(weatherEntity.getWeatherId());
        Assertions.assertThat(weatherDto.getCityName()).isEqualTo(weatherEntity.getCityName());
    }
}