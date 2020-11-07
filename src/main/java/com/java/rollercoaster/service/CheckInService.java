package com.java.rollercoaster.service;

public interface CheckInService {
    Error checkTicket(String ticketId);
    Error checkAppointments(String appointmentId, String eventName);
}
