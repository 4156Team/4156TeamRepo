package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.WeatherService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/weather")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class WeatherController {
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private WeatherService weatherService;
    @PostMapping("/query")
    @ResponseBody
    public CommonReturnType queryWeather(@RequestBody Date date)
            throws ParseException, BusinessException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.parse(sdf.format(date));
        Date today = sdf.parse(sdf.format(new Date()));

        //check login
        Boolean isLogin = (Boolean) httpServletRequest
                .getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }

        return CommonReturnType.create(weatherService.queryWeather(date));
    }


}
