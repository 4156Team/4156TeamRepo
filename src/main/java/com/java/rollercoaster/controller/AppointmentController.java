package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AppointmentService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/appointment")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    private HttpServletRequest httpServletRequest;


    @PostMapping("/addAppointment")
    @ResponseBody
    public CommonReturnType addAppointment(@RequestBody Appointment appointment){
        System.out.println(appointment.toString());
        return CommonReturnType.autoCreate(appointmentService.addAppointment(appointment));
    }

    @PostMapping("/updateAppointment")
    @ResponseBody
    public CommonReturnType updateAppointment(@RequestBody Appointment appointment){
        return CommonReturnType.autoCreate(appointmentService.updateAppointment(appointment));
    }

    @PostMapping("/deleteAppointment")
    @ResponseBody
    public CommonReturnType deleteAppointmentId(@RequestBody String appointmentId){
        return CommonReturnType.autoCreate(appointmentService.deleteAppointment(appointmentId));
    }

    /**
     *Display user's history tickets records.
     * @return response with common return type
     * @throws BusinessException exception handler
     */
    @RequestMapping("/appointmentsRecord")
    @ResponseBody
    public CommonReturnType getTickets() throws BusinessException {
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
        List<Appointment> appointmentList = appointmentService.getAppointmentsByUserId(userModel.getUserId());
        return CommonReturnType.create(appointmentList);
    }

}
