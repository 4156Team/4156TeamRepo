package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.CommentService;
import com.java.rollercoaster.service.QueryService;
import com.java.rollercoaster.service.RatingService;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller("rating")
@RequestMapping("/rating")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class RatingController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private RatingService ratingService;


    /**
     * Post rate endpoint.
     * @param facilityName facility to be rated
     * @param rate rate star
     * @return common return type
     */
    @PostMapping("/postRate")
    @ResponseBody
    public CommonReturnType postRating(@RequestBody  String facilityName,
                                       @RequestParam(name = "rate") String rate) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin)  {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        return CommonReturnType.autoCreate(ratingService.rateFacility(facilityName, Integer.parseInt(rate)));
    }

}
