package com.java.rollercoaster.controller;


import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.WeatherService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);

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

        try {
            CommonReturnType result =
                    CommonReturnType.create(weatherService.queryWeather(date));
            return result;
        } catch (BusinessException businessException) {
            return CommonReturnType.autoCreate((ErrorEnum) businessException.getCommonError());

        }
    }

}


