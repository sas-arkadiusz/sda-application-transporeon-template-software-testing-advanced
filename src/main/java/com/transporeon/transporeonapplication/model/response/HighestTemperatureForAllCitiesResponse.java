package com.transporeon.transporeonapplication.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HighestTemperatureForAllCitiesResponse {

    private final List<HighestTemperatureResponse> highestTemperatureForAllCities;
}
