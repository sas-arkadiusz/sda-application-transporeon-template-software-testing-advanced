package com.transporeon.transporeonapplication.factory;

import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherEntityTestFactory {

    public static WeatherEntity buildValidWeatherEntity() {
        final WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setWeatherId(1);
        weatherEntity.setCityName("Warsaw");
        weatherEntity.setTemp(17.5);
        weatherEntity.setDate(Timestamp.valueOf(LocalDateTime.of(2022, 1, 1, 12, 0, 0)));

        return weatherEntity;
    }
}
