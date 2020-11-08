package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.enumeration.Status;
import com.java.rollercoaster.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private TicketMapper ticketMapper;
    @Override
    public ErrorEnum checkTicket(String ticketId) {
        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        if (null == ticket){
            return ErrorEnum.WRONG_TICKET_ID;
        } else if (Status.used == ticket.getStatus()){
            return ErrorEnum.INVALID_TICKET;
        } else{
            System.out.println("ddd");
        }
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum checkAppointments(String appointmentId, String eventName) {
        return null;
    }
}
