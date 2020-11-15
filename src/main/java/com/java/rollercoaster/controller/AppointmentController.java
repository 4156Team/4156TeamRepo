package com.java.rollercoaster.controller;

import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;


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

}
