package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.QuickPassMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.CheckInService;
import com.java.rollercoaster.service.model.enumeration.Status;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private QuickPassMapper quickPassMapper;

    @Override
    public ErrorEnum checkTicket(String ticketId) {
        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        if (null == ticket) {
            return ErrorEnum.WRONG_TICKET_ID;
        } else if (Status.used == ticket.getStatus()) {
            return ErrorEnum.USED_TICKET;
        } else if (!DateUtils.isSameDay(new Date(), ticket.getValidDate())) {
            return ErrorEnum.INVALID_TICKET;
        } else {
            Ticket usedTicket = new Ticket();
            usedTicket.setTicketId(ticket.getTicketId());
            usedTicket.setStatus(Status.used);
            ticketMapper.updateByPrimaryKeySelective(usedTicket);
        }
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum checkAppointments(String appointmentId) {
        Appointment appointment = appointmentMapper.selectByPrimaryKey(appointmentId);
        if (null == appointment) {
            return ErrorEnum.WRONG_APPOINTMENT_ID;
        } else if (!DateUtils.isSameDay(new Date(), appointment.getValidDate())) {
            return ErrorEnum.INVALID_APPOINTMENT;
        } else {
            appointmentMapper.deleteByPrimaryKey(appointmentId);
        }
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum checkQuickPass(String quickPassId) {
        QuickPass quickPass = quickPassMapper.selectByPrimaryKey(quickPassId);
        if (null == quickPass) {
            return ErrorEnum.QUICKPASS_NOT_EXIST;
        } else if (new Date().getTime() < quickPass.getStartTime().getTime()
                || new Date().getTime()
                > quickPass.getStartTime().getTime() + 30 * 60 * 1000) {
            return ErrorEnum.INVALID_QUICKPASS;
        } else {
            quickPassMapper.deleteByPrimaryKey(quickPassId);
        }
        return ErrorEnum.OK;
    }
}
