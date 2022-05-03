package com.transporeon.transporeonapplication.exception;

public class WeatherAlreadyExistsException extends Exception {

    public WeatherAlreadyExistsException() {
        super("Weather for the given city or coordinates already exists!");
    }
}
