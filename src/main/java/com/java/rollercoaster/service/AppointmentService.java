package com.java.rollercoaster.service;

import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;

public interface AppointmentService {
    ErrorEnum addAppointment(Appointment appointment);
    ErrorEnum updateAppointment(Appointment appointment);
    //update的时候对于传入的是null的属性不进行修改
    ErrorEnum deleteAppointment(String appointmentId);
}
