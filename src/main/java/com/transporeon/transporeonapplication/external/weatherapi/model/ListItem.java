package com.transporeon.transporeonapplication.external.weatherapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ListItem {

    private long dt;
    private Main main;
    private Wind wind;
}
