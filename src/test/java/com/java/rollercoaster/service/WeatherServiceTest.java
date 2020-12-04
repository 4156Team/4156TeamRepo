package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.service.model.WeatherModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    void queryWeatherTest() throws ParseException, BusinessException {

        long millis = System.currentTimeMillis();
        long later = millis + 86400000L;
        WeatherModel weatherModel = weatherService.queryWeather(new Date(later));
        assertNotNull(weatherModel.getWeather());
        System.out.println(weatherModel.getWeather());

    }

    @Test
    void queryWrongDateWeatherTest() throws ParseException {

        long millis = System.currentTimeMillis();
        long before = millis - 86400000L;
        try {
            weatherService.queryWeather(new Date(before));
        } catch (BusinessException businessException) {
            assertEquals(256, businessException.getErrCode());
        }

    }
}