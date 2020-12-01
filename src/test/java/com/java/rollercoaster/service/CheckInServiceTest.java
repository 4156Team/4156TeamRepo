package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.model.enumeration.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CheckInServiceTest {

    @Autowired
    CheckInService checkInService;
    @Autowired
    AppointmentMapper appointmentMapper;
    @Autowired
    TicketMapper ticketMapper;



    @Test
    public void  checkTicketNormal() {
        Ticket ticket = new Ticket();
        ticket.setTicketId("100");
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 20);
        ticket.setUserId(1);
        ticket.setValidDate(new Date());
        ticketMapper.insertSelective(ticket);
        assertEquals(ErrorEnum.OK, checkInService.checkTicket("100"));
        ticketMapper.deleteByPrimaryKey("100");
    }

    @Test
        public void  checkTicketWithWrongTicketID() {
        assertEquals(ErrorEnum.WRONG_TICKET_ID, checkInService.checkTicket("100000"));
    }

    @Test
    public void  checkTicketWithInvalidTicketLastDay() throws ParseException {
        Ticket ticket2 = new Ticket();
        ticket2.setTicketId("300");
        ticket2.setStatus(Status.unused);
        ticket2.setPrice((float) 20);
        ticket2.setUserId(1);
        ticket2.setValidDate(new Date(new Date().getTime() - 24 * 60 * 60 * 1000));
        ticketMapper.insertSelective(ticket2);
        System.out.println("init finished.");
        assertEquals(ErrorEnum.INVALID_TICKET, checkInService.checkTicket("300"));
        ticketMapper.deleteByPrimaryKey("300");
    }

    @Test
    public void  checkTicketWithInvalidTicketNextDay() throws ParseException {
        Ticket ticket2 = new Ticket();
        ticket2.setTicketId("400");
        ticket2.setStatus(Status.unused);
        ticket2.setPrice((float) 20);
        ticket2.setUserId(1);
        ticket2.setValidDate(new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
        ticketMapper.insertSelective(ticket2);
        System.out.println("init finished.");
        assertEquals(ErrorEnum.INVALID_TICKET, checkInService.checkTicket("400"));
        ticketMapper.deleteByPrimaryKey("400");
    }

    @Test
    public void checkTicketWithUsedTicket() {
        Ticket ticket1 = new Ticket();
        ticket1.setTicketId("200");
        ticket1.setStatus(Status.used);
        ticket1.setPrice((float) 20);
        ticket1.setUserId(1);
        ticket1.setValidDate(new Date());
        ticketMapper.insertSelective(ticket1);
        assertEquals(ErrorEnum.USED_TICKET, checkInService.checkTicket("200"));
        ticketMapper.deleteByPrimaryKey("200");
    }





    @Test
    public void checkAppointmentNormal() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("10");
        appointment.setEventName("test");
        appointment.setUserId(1);
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);
        assertEquals(ErrorEnum.OK,checkInService.checkAppointments("10"));
        appointmentMapper.deleteByPrimaryKey("10");
    }

    @Test
    public void checkAppointmentWithWrongAppointmentId() {
        assertEquals(ErrorEnum.WRONG_APPOINTMENT_ID,checkInService.checkAppointments("00"));
    }

    @Test
    public void checkAppointmentWithLastDay() throws ParseException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("20");
        appointment.setEventName("test");
        appointment.setUserId(1);
        appointment.setValidDate(new Date(new Date().getTime() - 24 * 60 * 60 * 1000));
        appointmentMapper.insertSelective(appointment);
        assertEquals(ErrorEnum.INVALID_APPOINTMENT,checkInService.checkAppointments("20"));
        appointmentMapper.deleteByPrimaryKey("20");
    }

    @Test
    public void checkAppointmentWithNextDay() throws ParseException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("30");
        appointment.setEventName("test");
        appointment.setUserId(1);
        appointment.setValidDate(new Date(new Date().getTime() + 24 * 60 * 60 * 1000));
        appointmentMapper.insertSelective(appointment);
        assertEquals(ErrorEnum.INVALID_APPOINTMENT,checkInService.checkAppointments("30"));
        appointmentMapper.deleteByPrimaryKey("30");
    }
}