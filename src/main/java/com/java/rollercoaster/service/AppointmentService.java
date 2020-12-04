package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;

import java.util.List;

public interface AppointmentService {

    String addAppointment(Appointment appointment) throws BusinessException;
    //return appointment id

    ErrorEnum updateAppointment(Appointment appointment);
    //update的时候对于传入的是null的属性不进行修改

    ErrorEnum deleteAppointment(String appointmentId, UserModel userModel);

    List<TimedAppointmentModel> getAppointmentsByUserId(Integer userId) throws BusinessException;
}
