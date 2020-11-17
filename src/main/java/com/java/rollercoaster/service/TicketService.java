package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.model.UserModel;

import java.util.List;

public interface TicketService {
    ErrorEnum addTicket(Ticket ticket);
    ErrorEnum updateTicket(Ticket ticket);
    ErrorEnum deleteTicket(String ticketId, UserModel userModel);
    List<Ticket> getTicketsByUserId(Integer userId);
}
