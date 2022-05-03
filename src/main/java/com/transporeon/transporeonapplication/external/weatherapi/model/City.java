package com.transporeon.transporeonapplication.external.weatherapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class City {

    private Coordinates coord;
    private String name;
}
