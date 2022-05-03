package com.transporeon.transporeonapplication.external.weatherapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Wind {

    private int deg;
    private double speed;
}
