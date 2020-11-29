package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.service.model.WeatherModel;

import java.text.ParseException;
import java.util.Date;

public interface WeatherService {
    WeatherModel queryWeather(Date date) throws ParseException, BusinessException;
}
