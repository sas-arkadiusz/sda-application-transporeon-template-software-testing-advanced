package com.transporeon.transporeonapplication.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class HighestTemperatureResponse {

    private String cityName;
    private double highestTemperature;
}
