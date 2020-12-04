package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.model.UserModel;

import java.text.ParseException;
import java.util.List;

public interface TicketService {

    String addTicket(Ticket ticket, int userId) throws ParseException, BusinessException;

    ErrorEnum updateTicket(Ticket ticket);

    ErrorEnum deleteTicket(String ticketId, UserModel userModel);

    List<Ticket> getTicketsByUserId(Integer userId);
}
