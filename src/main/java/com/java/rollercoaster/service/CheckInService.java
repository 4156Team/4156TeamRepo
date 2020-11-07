package com.java.rollercoaster.service;

import com.java.rollercoaster.errorEnum.ErrorEnum;

public interface CheckInService {
    ErrorEnum checkTicket(String ticketId);
    ErrorEnum checkAppointments(String appointmentId, String eventName);
}
