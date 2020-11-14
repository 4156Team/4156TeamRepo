package com.java.rollercoaster.service;

import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import java.util.Date;

public interface TicketService {
    ErrorEnum addTicket(Ticket ticket);
    ErrorEnum updateTicket(Ticket ticket);
    ErrorEnum deleteTicket(String ticketId);
}
