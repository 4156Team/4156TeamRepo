package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;

public interface TicketService {
    ErrorEnum addTicket(Ticket ticket);
    ErrorEnum updateTicket(Ticket ticket);
    ErrorEnum deleteTicket(String ticketId);
}
