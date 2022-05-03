package com.transporeon.transporeonapplication.controller;

import com.transporeon.transporeonapplication.exception.WeatherNotFoundException;
import com.transporeon.transporeonapplication.exception.WeatherAlreadyExistsException;
import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.request.WeatherRequestWithCityName;
import com.transporeon.transporeonapplication.model.request.WeatherRequestWithCoordinates;
import com.transporeon.transporeonapplication.model.response.HighestTemperatureForAllCitiesResponse;
import com.transporeon.transporeonapplication.model.response.HighestTemperatureResponse;
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
    public ResponseEntity<List<WeatherDto>> getAllWeatherRecords() {
        return ResponseEntity.ok(weatherService.getAllWeatherRecords());
    }

    @GetMapping("/{cityName}")
    public ResponseEntity<WeatherDto> getWeatherForCity(@PathVariable final String cityName) {
        try {
            return ResponseEntity.ok(weatherService.getWeatherForGivenCity(cityName));
        } catch (WeatherNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{cityName}/{date}")
    public ResponseEntity<WeatherDto> getWeatherForCityAndDate(@PathVariable final String cityName,
                                                               @PathVariable final Timestamp date) {
        try {
            return ResponseEntity.ok(weatherService.getWeatherForGivenCityAndDate(cityName, date));
        } catch (WeatherNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/temperature/highest")
    public ResponseEntity<HighestTemperatureResponse> getHighestTemperature() {
        try {
            return ResponseEntity.ok(weatherService.getCityWithTheHighestTemperature());
        } catch (WeatherNotFoundException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    TO-DO: Implement the method which finds the highest temperature for each city stored in the database.
           If you think some parameters are necessary for this controller method, please add those parameters.
           Create appropriate service, repository methods and helper classes if needed. Decide if each
           created method should be tested or not. Decide if created tests should contain mocks or even
           the created test should be integration tests.
    */
    @GetMapping("/temperature/highest/all")
    public ResponseEntity<HighestTemperatureForAllCitiesResponse> getHighestTemperaturesForAllCities() {
        return ResponseEntity.noContent().build();
    }

    /*
    TO-DO: Implement the method which finds the information related to the WeatherEntity for the city
           with the lowest temperature available in the database. If you think some parameters are necessary
           for this controller method, please add those parameters. Create appropriate service, repository
           methods and helper classes if needed. Decide if each created method should be tested or not.
           Decide if created tests should contain mocks or even the created test should be integration tests.

           Do not forget to create also the Response class.
    */
    @GetMapping("/temperature/lowest")
    public ResponseEntity<Void> getLowestTemperature() {
        return ResponseEntity.noContent().build();
    }

    /*
       TO-DO: Implement the method which finds the average temperature for the provided city.
       If you think some parameters are necessary for this controller method, please add those parameters.
       Create appropriate service, repository methods and helper classes if needed. Decide if each
       created method should be tested or not. Decide if created tests should contain mocks or even
       the created test should be integration tests.
    */
    @GetMapping("/temperature/average/{cityName}")
    public ResponseEntity<Void> getAverageTemperatureForCity(@RequestParam String cityName) {
        return ResponseEntity.noContent().build();
    }

    /*
    TO-DO: Implement the method which finds the average temperature for each city stored in the database.
           If you think some parameters are necessary for this controller method, please add those parameters.
           Create appropriate service, repository methods and helper classes if needed. Decide if each
           created method should be tested or not. Decide if created tests should contain mocks or even
           the created test should be integration tests.
     */
    @GetMapping("/temperature/average/all")
    public ResponseEntity<Void> getAverageTemperatureForAllCities() {
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/withCityName")
    public ResponseEntity<Void> addWeatherWithCityName(@RequestBody final WeatherRequestWithCityName request) {
        try {
            weatherService.addWeather(request);
            return ResponseEntity.ok().build();
        } catch (WeatherAlreadyExistsException exception) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    /*
    TO-DO: Implement the method which creates a weather entity record in the database using the coordinates.
           If you want to adjust create request parameters feel free to adjust the WeatherRequestWithCoordinates class.
           If you think some parameters are necessary for this controller method, please add those parameters.
           Create appropriate service, repository methods and helper classes if needed. Decide if each
           created method should be tested or not. Decide if created tests should contain mocks or even
           the created test should be integration tests.
    */
    @PostMapping("/withCoordinates")
    public ResponseEntity<Void> addWeatherWithCoordinates(@RequestBody final WeatherRequestWithCoordinates request) {
        return ResponseEntity.noContent().build();
    }

    /*
    TO-DO: Implement the method which deletes all the weather records for the specific city. If you think some
           parameters are necessary for this controller method, please add those parameters. Create appropriate
           service, repository methods and helper classes if needed. Decide if each created method should be
           tested or not. Decide if created tests should contain mocks or even the created test should be integration tests.
     */
    @DeleteMapping("/{cityName}")
    public ResponseEntity<Void> deleteAllWeatherForCity() {
        return ResponseEntity.noContent().build();
    }

    /*
    TO-DO: Implement the method which deletes all the weather records for the specific city within provided date.
           If you think some parameters are necessary for this controller method, please add those parameters.
           Create appropriate service, repository methods and helper classes if needed. Decide if each created
           method should be tested or not. Decide if created tests should contain mocks or even the created test
           should be integration tests.
    */
    @DeleteMapping("/{cityName}/{date}")
    public ResponseEntity<Void> deleteAllWeatherForCityAndDate() {
        return ResponseEntity.noContent().build();
    }
}
