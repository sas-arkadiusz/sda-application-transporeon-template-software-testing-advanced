package com.transporeon.transporeonapplication.controller;

import com.transporeon.transporeonapplication.exception.WeatherNotFoundException;
import com.transporeon.transporeonapplication.exception.WeatherAlreadyExistsException;
import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import com.transporeon.transporeonapplication.model.request.WeatherRequest;
import com.transporeon.transporeonapplication.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/api/weather", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping()
    public ResponseEntity<List<WeatherEntity>> getAllWeatherRecords() {
        return ResponseEntity.ok(weatherService.getAllWeatherRecords());
    }

    @GetMapping("/{cityName}/{date}")
    public ResponseEntity<WeatherEntity> getWeatherForCityAndDate(@PathVariable final String cityName,
                                                                  @PathVariable final Timestamp date) {
        try {
            return ResponseEntity.ok(weatherService.getWeatherForGivenCityAndDate(cityName, date));
        } catch (WeatherNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/warm")
    public ResponseEntity<WeatherEntity> getHottestCity() {
        try {
            return ResponseEntity.ok(weatherService.getHottestCity());
        } catch (WeatherNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<WeatherEntity> addWeather(@RequestBody final WeatherRequest request) throws WeatherAlreadyExistsException {
        try {
            weatherService.addWeather(request);
            return ResponseEntity.ok().build();
        } catch (WeatherAlreadyExistsException exception) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
