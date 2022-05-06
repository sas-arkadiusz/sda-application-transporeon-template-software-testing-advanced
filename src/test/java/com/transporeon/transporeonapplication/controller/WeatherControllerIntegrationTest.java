package com.transporeon.transporeonapplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transporeon.transporeonapplication.model.request.WeatherRequestWithCityName;
import com.transporeon.transporeonapplication.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Start the application and its context
@AutoConfigureMockMvc // Configure MVC and HTTP request related things
class WeatherControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addWeatherWithCityNameAddsRecordToDatabase() throws Exception {
        final WeatherRequestWithCityName request = WeatherRequestWithCityName.of("Warsaw", Timestamp.from(Instant.now()));

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:" + port + "/api/weather/withCityName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:" + port + "/api/weather/"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].cityName").value("Warsaw"));

    }
}