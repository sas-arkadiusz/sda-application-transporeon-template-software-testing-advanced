package com.transporeon.transporeonapplication.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherRequestWithCoordinates {

    private final String cityName;
    private final double lon;
    private final double lat;
}
