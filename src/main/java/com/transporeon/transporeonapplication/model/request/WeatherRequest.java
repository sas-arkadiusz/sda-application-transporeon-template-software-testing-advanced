package com.transporeon.transporeonapplication.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class WeatherRequest {

    private final String cityName;
    private final Timestamp date;
}
