package com.transporeon.transporeonapplication.mapper;

import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.response.HighestTemperatureResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HighestTemperatureResponseMapper {

    public static HighestTemperatureResponse map(final WeatherDto weatherDto) {
        return HighestTemperatureResponse.of(weatherDto.getCityName(), weatherDto.getTemp());
    }
}
