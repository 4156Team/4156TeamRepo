package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AppointmentService;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public CommonReturnType addAppointment(@RequestBody Appointment appointment)
            throws BusinessException {
        System.out.println(appointment.toString());
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
        appointment.setAppointmentid(id);
        return CommonReturnType.create(appointmentService.addAppointment(appointment));
    }

    /**
     * End point to update an appointment given an appointment object.
     * @param appointment an appointment object
     * @return CommonReturnType
     * @throws BusinessException a BusinessException object
     */
    @PostMapping("/updateAppointment")
    @ResponseBody
    public CommonReturnType updateAppointment(@RequestBody Appointment appointment)
            throws BusinessException {
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
        //only manager or the same visitor can update the appointment
        if (userModel.getRole() == Role.visitor && !userModel.getUserId()
                .equals(appointment.getUserId()) ) {
            throw new BusinessException(ErrorEnum.NOT_SAME_VISITOR);
        }
        return CommonReturnType.autoCreate(appointmentService.updateAppointment(appointment));
    }

    /**
     * End point to delete an appointment.
     * @param appointmentId the appointmentId of the appointment you want to delete
     * @return CommonReturnType
     * @throws BusinessException A BusinessException object
     */
    @PostMapping("/deleteAppointment")
    @ResponseBody
    public CommonReturnType deleteAppointmentId(@RequestBody String appointmentId)
            throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }

        //only manager or the same visitor can delete the appointment
        return CommonReturnType.autoCreate(appointmentService
                .deleteAppointment(appointmentId, userModel));
    }

    /**
     *Display user's history tickets records.
     * @return response with common return type
     * @throws BusinessException exception handler
     */
    @RequestMapping("/appointmentsRecord")
    @ResponseBody
    public CommonReturnType getAppointments() throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        List<Appointment> appointmentList = appointmentService
                .getAppointmentsByUserId(userModel.getUserId());
        return CommonReturnType.create(appointmentList);
    }

}
