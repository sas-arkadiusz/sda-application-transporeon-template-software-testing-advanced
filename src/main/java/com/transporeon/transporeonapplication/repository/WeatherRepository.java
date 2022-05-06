package com.transporeon.transporeonapplication.repository;

import com.transporeon.transporeonapplication.model.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Integer> {

    Optional<WeatherEntity> findByCityName(String cityName);

    @Query("SELECT we FROM WeatherEntity we WHERE we.cityName = ?1 AND we.date = ?2")
    WeatherEntity findByCityNameAndDate(String cityName, Timestamp date);

    @Query("SELECT we FROM WeatherEntity we WHERE we.cityName = ?1 AND we.temp = ?2")
    WeatherEntity findByCityNameAndTemp(String cityName, double temp);

    @Query(value = "SELECT * FROM weather_entity ORDER BY temp DESC LIMIT 1", nativeQuery = true)
    WeatherEntity findByHighestTemp();

    // When should we create a test?
}
