package com.transporeon.transporeonapplication.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weatherId;

    private double longitude;

    private double latitude;

    private String cityName;

    private Timestamp date;

    private double temp;

    private int humidity;

    private int pressure;

    private int windDeg;

    private double windSpeed;
}
