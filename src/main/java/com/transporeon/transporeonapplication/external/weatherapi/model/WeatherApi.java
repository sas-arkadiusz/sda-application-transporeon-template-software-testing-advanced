package com.transporeon.transporeonapplication.external.weatherapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WeatherApi {

    private City city;
    private List<ListItem> list;
}
