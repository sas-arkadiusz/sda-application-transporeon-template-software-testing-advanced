package com.transporeon.transporeonapplication.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor(staticName = "of")
public class WeatherRequestWithCityName {

    private final String cityName;
    private final Timestamp date;
}
