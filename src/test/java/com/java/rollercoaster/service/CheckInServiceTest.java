package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.enumeration.Status;
import org.junit.jupiter.api.*;
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
class CheckInServiceTest {

    @Autowired
    CheckInService checkInService;
    @Autowired
    AppointmentMapper appointmentMapper;
    @Autowired
    TicketMapper ticketMapper;

    @BeforeEach
    void init() throws ParseException {
        Appointment appointment = new Appointment();
        appointment.setAppointmentid("10");
        appointment.setEventName("test");
        appointment.setUserId(1);
        appointmentMapper.insertSelective(appointment);

        Ticket ticket = new Ticket();
        ticket.setTicketId("100");
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 20);
        ticket.setUserId(1);
        ticket.setValidDate(new Date());
        ticketMapper.insertSelective(ticket);

        Ticket ticket1 = new Ticket();
        ticket1.setTicketId("200");
        ticket1.setStatus(Status.used);
        ticket1.setPrice((float) 20);
        ticket1.setUserId(1);
        ticket1.setValidDate(new Date());
        ticketMapper.insertSelective(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setTicketId("300");
        ticket2.setStatus(Status.unused);
        ticket2.setPrice((float) 20);
        ticket2.setUserId(1);
        ticket2.setValidDate(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-20"));
        ticketMapper.insertSelective(ticket2);
        System.out.println("init finished.");
    }
    @Test
    void checkTicket() {
        assertEquals(ErrorEnum.OK, checkInService.checkTicket("100"));
        assertEquals(ErrorEnum.WRONG_TICKET_ID, checkInService.checkTicket("100000"));
        assertEquals(ErrorEnum.INVALID_TICKET, checkInService.checkTicket("300"));
        assertEquals(ErrorEnum.USED_TICKET, checkInService.checkTicket("200"));
    }

    @Test
    void checkAppointments() {
        assertEquals(ErrorEnum.WRONG_APPOINTMENT_ID,checkInService.checkAppointments("00"));
        assertEquals(ErrorEnum.OK,checkInService.checkAppointments("10"));
    }

    @AfterEach
    void end(){
        ticketMapper.deleteByPrimaryKey("100");
        ticketMapper.deleteByPrimaryKey("200");
        ticketMapper.deleteByPrimaryKey("300");
        appointmentMapper.deleteByPrimaryKey("10");
        System.out.println("Finished");
    }
}