package com.transporeon.transporeonapplication.exception;

public class WeatherNotFoundException extends Exception {

    public WeatherNotFoundException() {
        super("Weather not found!");
    }
}
