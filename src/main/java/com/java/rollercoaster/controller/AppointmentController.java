package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AppointmentService;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/appointment")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * End point to add a new appointment given appointment object.
     * @param appointment an appointment object
     * @return CommonReturnType
     * @throws BusinessException a BusinessException object
     */
    @PostMapping("/addAppointment")
    @ResponseBody
    public CommonReturnType addAppointment(@RequestBody Appointment appointment) {
        System.out.println(appointment.toString());
        Boolean isLogin = (Boolean) httpServletRequest
                .getSession().getAttribute("IS_LOGIN");
        if (isLogin == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }

        if (!isLogin) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        appointment.setUserId(userModel.getUserId());
        long millis = System.currentTimeMillis();
        //加上四位随机数
        Random random = new Random();
        int end4 = random.nextInt(9999);
        //如果不足两位前面补0
        String timestamp = String.valueOf(millis);
        String id = timestamp.substring(timestamp.length() - 6)
                + String.format("%04d", end4);
        System.out.println(id);


        appointment.setAppointmentId(id);
        try {
            CommonReturnType result =
                    CommonReturnType.create(appointmentService.addAppointment(appointment));
            return result;
        } catch (BusinessException businessException) {
            return CommonReturnType.autoCreate((ErrorEnum) businessException.getCommonError());

        }

    }

    /**
     * End point to update an appointment given an appointment object.
     * @param appointment an appointment object
     * @return CommonReturnType
     */
    @PostMapping("/updateAppointment")
    @ResponseBody
    public CommonReturnType updateAppointment(@RequestBody Appointment appointment) {
        Boolean isLogin = (Boolean) httpServletRequest
                .getSession().getAttribute("IS_LOGIN");
        if (isLogin == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }

        if (!isLogin) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        //only manager or the same visitor can update the appointment
        if (userModel.getRole() == Role.visitor && !userModel.getUserId()
                .equals(appointment.getUserId()) ) {
            return CommonReturnType.autoCreate(ErrorEnum.NOT_SAME_VISITOR);
        }
        return CommonReturnType.autoCreate(appointmentService.updateAppointment(appointment));
    }

    /**
     * End point to delete an appointment.
     * @param appointmentId the appointmentId of the appointment you want to delete
     * @return CommonReturnType
     */
    @PostMapping("/deleteAppointment")
    @ResponseBody
    public CommonReturnType deleteAppointmentId(@RequestParam(name = "appointmentId")
                                                            String appointmentId) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }

        if (!isLogin) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }

        //only manager or the same visitor can delete the appointment
        return CommonReturnType.autoCreate(appointmentService
                .deleteAppointment(appointmentId, userModel));
    }

    /**
     *Display user's history tickets records.
     * @return response with common return type
     */
    @RequestMapping("/appointmentsRecord")
    @ResponseBody
    public CommonReturnType getAppointments() {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }

        if (!isLogin) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }

        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        try {
            List<TimedAppointmentModel> timedAppointmentModelListppointmentList = appointmentService
                    .getAppointmentsByUserId(userModel.getUserId());
            return CommonReturnType.create(timedAppointmentModelListppointmentList);
        } catch (BusinessException businessException) {
            return CommonReturnType.autoCreate((ErrorEnum) businessException.getCommonError());
        }

    }

}
