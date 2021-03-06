package com.java.rollercoaster.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WeatherModel {
    @JsonFormat()
    private Date date;
    private Float maxTemp;
    private Float minTemp;
    private String weather;

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public Float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Float minTemp) {
        this.minTemp = minTemp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

}
