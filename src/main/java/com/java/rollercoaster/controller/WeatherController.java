package com.java.rollercoaster.controller;


import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.WeatherService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private WeatherService weatherService;

    /**
     * End point to query the weather for a certain date.
     *
     * @param dateString the date you want to know the weather of
     *                   you can use Long time = System.currentTimeMillis();
     *                   and String dateString = time.toString();
     * @return a CommonReturnType with weather model contained if succeed
     * @throws ParseException    throw exception for date parse
     * @throws BusinessException throw Business exception for login issue
     */
    @PostMapping("/query")
    @ResponseBody
    public CommonReturnType queryWeather(@RequestParam(name = "date") String dateString)
            throws ParseException, BusinessException {
        Long dateLong = Long.parseLong(dateString);
        Date date = new Date(dateLong);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.parse(sdf.format(date));
        Date today = sdf.parse(sdf.format(new Date()));

        //check login
        Boolean isLogin = (Boolean) httpServletRequest
                .getSession().getAttribute("IS_LOGIN");
        if (!isLogin) {
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
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);
        CustomDateEditor dateEditor = new CustomDateEditor(simpleDateFormat, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
}


