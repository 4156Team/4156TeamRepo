package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;

public interface CheckInService {
    ErrorEnum checkTicket(String ticketId);

    ErrorEnum checkAppointments(String appointmentId);

    ErrorEnum checkQuickPass(String quickPassId);
}
