package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Ticket;

import java.util.List;

public interface AppointmentService {
    ErrorEnum addAppointment(Appointment appointment);
    ErrorEnum updateAppointment(Appointment appointment);
    //update的时候对于传入的是null的属性不进行修改
    ErrorEnum deleteAppointment(String appointmentId);
    List<Appointment> getAppointmentsByUserId(Integer userId);
}
