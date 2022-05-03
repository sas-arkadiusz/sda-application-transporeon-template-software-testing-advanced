package com.transporeon.transporeonapplication.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class WeatherDto {

    private int weatherId;
    private String cityName;
    private double lon;
    private double lat;
    private Timestamp date;
    private double temp;
    private int humidity;
    private int pressure;
    private int windDeg;
    private double windSpeed;
}
