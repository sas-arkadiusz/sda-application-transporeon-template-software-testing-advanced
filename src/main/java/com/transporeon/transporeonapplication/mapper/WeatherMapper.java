package com.transporeon.transporeonapplication.mapper;

import com.transporeon.transporeonapplication.external.weatherapi.model.City;
import com.transporeon.transporeonapplication.external.weatherapi.model.ListItem;
import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherMapper {

    public static WeatherDto map(final WeatherEntity weatherEntity) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setWeatherId(weatherEntity.getWeatherId());
        weatherDto.setCityName(weatherEntity.getCityName());
        weatherDto.setLon(weatherEntity.getLongitude());
        weatherDto.setLat(weatherEntity.getLatitude());
        weatherDto.setDate(weatherEntity.getDate());
        weatherDto.setTemp(weatherEntity.getTemp());
        weatherDto.setHumidity(weatherEntity.getHumidity());
        weatherDto.setPressure(weatherEntity.getPressure());
        weatherDto.setWindDeg(weatherEntity.getWindDeg());
        weatherDto.setWindSpeed(weatherEntity.getWindSpeed());

        return weatherDto;
    }

    public static WeatherEntity map(final ListItem listItem, final City city) {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCityName(city.getName());
        weatherEntity.setLongitude(city.getCoord().getLon());
        weatherEntity.setLatitude(city.getCoord().getLat());
        weatherEntity.setDate(parseUnixTimestampToSqlTimestamp(listItem.getDt()));
        weatherEntity.setTemp(listItem.getMain().getTemp());
        weatherEntity.setHumidity(listItem.getMain().getHumidity());
        weatherEntity.setPressure(listItem.getMain().getPressure());
        weatherEntity.setWindDeg(listItem.getWind().getDeg());
        weatherEntity.setWindSpeed(listItem.getWind().getSpeed());

        return weatherEntity;
    }

    private static Timestamp parseUnixTimestampToSqlTimestamp(long unixTimestamp) {
        return Timestamp.from(Instant.ofEpochSecond(unixTimestamp));
    }
}
