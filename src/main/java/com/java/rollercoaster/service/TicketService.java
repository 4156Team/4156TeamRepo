package com.java.rollercoaster.service;

import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import java.util.Date;

public interface TicketService {
    ErrorEnum addTicket(Ticket ticket);
    ErrorEnum updateTicket(Ticket ticket);
    ErrorEnum deleteTicket(Integer userId, Date validDate);
    //每个userId和validDate对应唯一票
}
