package com.transporeon.transporeonapplication.external.weatherapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Main {

    private double temp;
    private int humidity;
    private int pressure;
}
