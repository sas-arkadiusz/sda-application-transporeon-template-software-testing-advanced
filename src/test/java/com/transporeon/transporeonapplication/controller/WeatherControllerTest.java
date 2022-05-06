package com.transporeon.transporeonapplication.controller;

import com.transporeon.transporeonapplication.model.dto.WeatherDto;
import com.transporeon.transporeonapplication.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class) // 1. Add annotation to start dependencies necessary for Controllers
class WeatherControllerTest {

    @MockBean // 2. Mock all the dependencies
    private WeatherService weatherService;

    @Autowired // 3. Inject the mockMvc
    private MockMvc mockMvc;

    @Test
    void getAllWeatherRecordsReturnNoWeatherRecordsAndRespondsWithOkWhenWeatherRecordsAreNotCreated() throws Exception {
        Mockito.when(weatherService.getAllWeatherRecords())
                .thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/weather"))
                .andDo(print())
                .andExpect(content().string(containsString("[]")))
                .andExpect(status().is(200));
    }
}