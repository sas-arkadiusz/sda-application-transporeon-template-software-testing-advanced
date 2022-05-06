package com.transporeon.transporeonapplication.service;

import com.transporeon.transporeonapplication.exception.WeatherNotFoundException;
import com.transporeon.transporeonapplication.exception.WeatherAlreadyExistsException;
import com.transporeon.transporeonapplication.external.weatherapi.model.WeatherApi;
import com.transporeon.transporeonapplication.external.weatherapi.service.WeatherApiService;
import com.transporeon.transporeonapplication.mapper.HighestTemperatureResponseMapper;
import com.transporeon.transporeonapplication.mapper.WeatherMapper;
import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import com.transporeon.transporeonapplication.model.request.WeatherRequestWithCityName;
import com.transporeon.transporeonapplication.model.response.HighestTemperatureResponse;
import com.transporeon.transporeonapplication.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherApiService weatherApiService;
    private final WeatherRepository weatherRepository;

    public List<WeatherDto> getAllWeatherRecords() {
        final List<WeatherEntity> allWeatherRecords = weatherRepository.findAll();
        return allWeatherRecords.stream()
                .map(WeatherMapper::map)
                .collect(Collectors.toList());
    }

    public WeatherDto getWeatherForGivenCity(final String cityName) throws WeatherNotFoundException {
        final WeatherEntity foundWeatherEntity = weatherRepository.findByCityName(cityName)
                .orElseThrow(WeatherNotFoundException::new);
        return WeatherMapper.map(foundWeatherEntity);
    }

    public WeatherDto getWeatherForGivenCityAndDate(final String cityName, final Timestamp date) throws WeatherNotFoundException {
        final WeatherEntity foundWeatherEntity = Optional.ofNullable(weatherRepository.findByCityNameAndDate(cityName, date))
                .orElseThrow(WeatherNotFoundException::new);
        return WeatherMapper.map(foundWeatherEntity);
    }

    public HighestTemperatureResponse getCityWithTheHighestTemperature() throws WeatherNotFoundException {
        final WeatherEntity foundWeatherEntity = Optional.ofNullable(weatherRepository.findByHighestTemp())
                .orElseThrow(WeatherNotFoundException::new);
        final WeatherDto weatherDto = WeatherMapper.map(foundWeatherEntity);
        return HighestTemperatureResponseMapper.map(weatherDto);
    }

    public void addWeather(final WeatherRequestWithCityName request) throws WeatherAlreadyExistsException {
        final Optional<WeatherEntity> weatherFromDatabase = Optional.ofNullable(weatherRepository.findByCityNameAndDate(request.getCityName(), request.getDate()));
        if (weatherFromDatabase.isPresent()) {
            throw new WeatherAlreadyExistsException();
        }

        final WeatherApi resultFromWeatherApi  = weatherApiService.getWeather(request.getCityName());
        if (resultFromWeatherApi != null) {
            addWeatherToDatabase(resultFromWeatherApi, request.getDate());
        }
    }
    private Iterable<WeatherEntity> addWeatherToDatabase(final WeatherApi weatherApi, final Timestamp date) {
        final LocalDate localDate = stringToLocalDate(date.toString());
        final List<WeatherEntity> weatherRecordsToSave = weatherApi.getList().stream()
                .filter(weather -> localDate.equals(epochToLocalDate(weather.getDt())))
                .map(weather -> WeatherMapper.map(weather, weatherApi.getCity()))
                .collect(Collectors.toList());

        return weatherRepository.saveAll(weatherRecordsToSave);
    }

    private LocalDate epochToLocalDate(Long epochDate) {
        return Instant.ofEpochSecond(epochDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSSS][.SSS][.SS][.S]"));
    }
}
