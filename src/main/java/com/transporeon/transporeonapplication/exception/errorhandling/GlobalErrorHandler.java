package com.transporeon.transporeonapplication.exception.errorhandling;

import com.transporeon.transporeonapplication.exception.WeatherAlreadyExistsException;
import com.transporeon.transporeonapplication.exception.WeatherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WeatherNotFoundException.class)
    public Error handleWeatherNotFoundException(final WeatherNotFoundException exception) {
        return new Error(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WeatherAlreadyExistsException.class)
    public Error handleWeatherNotFoundException(final WeatherAlreadyExistsException exception) {
        return new Error(exception.getMessage());
    }
}