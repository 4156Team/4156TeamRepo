package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.WeatherModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class WeatherControllerTest {

    @Autowired
    WeatherController weatherController;
    @Autowired
    HttpServletRequest httpServletRequest;

    @Test
    void queryWeatherControllerTest() throws ParseException, BusinessException {
        httpServletRequest
                .getSession().setAttribute("IS_LOGIN", true);
        UserModel userModel = new UserModel();
        userModel.setUserId(1);
        httpServletRequest
                .getSession().setAttribute("LOGIN_USER", userModel);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = sdf.format(new Date());
        CommonReturnType response = weatherController.queryWeather(timeString);
        assertEquals("success", response.getStatus());
        assertEquals(WeatherModel.class, response.getData().getClass());
        WeatherModel weather = (WeatherModel) response.getData();
        System.out.println(weather.getMaxTemp());
    }

    @Test
    void queryNullLoginWeatherControllerTest() throws ParseException, BusinessException {
        httpServletRequest
                .getSession().setAttribute("IS_LOGIN", null);
        UserModel userModel = new UserModel();
        userModel.setUserId(1);
        httpServletRequest
                .getSession().setAttribute("LOGIN_USER", userModel);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = sdf.format(new Date());
        try {
            weatherController.queryWeather(timeString);
        } catch (BusinessException businessException) {
            assertEquals(20003, businessException.getErrCode());
        }

    }

    @Test
    void queryFalseLoginWeatherControllerTest() throws ParseException, BusinessException {
        httpServletRequest
                .getSession().setAttribute("IS_LOGIN", false);
        UserModel userModel = new UserModel();
        userModel.setUserId(1);
        httpServletRequest
                .getSession().setAttribute("LOGIN_USER", userModel);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = sdf.format(new Date());
        try {
            weatherController.queryWeather(timeString);
        } catch (BusinessException businessException) {
            assertEquals(20003, businessException.getErrCode());
        }

    }

    @Test
    void queryNullUserWeatherControllerTest() throws ParseException, BusinessException {
        httpServletRequest
                .getSession().setAttribute("IS_LOGIN", true);
        UserModel userModel = new UserModel();
        userModel.setUserId(1);
        httpServletRequest
                .getSession().setAttribute("LOGIN_USER", null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = sdf.format(new Date());
        try {
            weatherController.queryWeather(timeString);
        } catch (BusinessException businessException) {
            assertEquals(20001, businessException.getErrCode());
        }

    }

    @Test
    void queryErrorWeatherControllerTest() throws ParseException, BusinessException {
        httpServletRequest
                .getSession().setAttribute("IS_LOGIN", true);
        UserModel userModel = new UserModel();
        userModel.setUserId(1);
        httpServletRequest
                .getSession().setAttribute("LOGIN_USER", userModel);
        long millis = System.currentTimeMillis();
        long before = millis - 2 * 86400000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeString = sdf.format(new Date(before));
        CommonReturnType response = weatherController.queryWeather(timeString);
        assertEquals(ErrorEnum.WRONG_DATE, response.getData());

    }
}