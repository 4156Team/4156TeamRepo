package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;

import java.util.List;

public interface TicketService {
    ErrorEnum addTicket(Ticket ticket);
    ErrorEnum updateTicket(Ticket ticket);
    ErrorEnum deleteTicket(String ticketId);
    List<Ticket> getTicketsByUserId(Integer userId);
}
