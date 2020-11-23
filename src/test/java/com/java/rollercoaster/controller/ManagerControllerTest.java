package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ManagerControllerTest {
    @Autowired
    private ManagerController managerController;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;

    public void init() throws ParseException {
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        event.setEventName("test");
        eventMapper.insert(event);

        Facility facility = new Facility();
        facility.setFacilityName("testFacility");
        facility.setFacilityIntroduction("testFacility");
        facility.setFacilityOpenTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        facility.setFacilityCloseTime(new SimpleDateFormat("HH-mm-ss").parse("11-00-00"));
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setQueueStatus(100);
        facilityMapper.insert(facility);

        Ticket ticket = new Ticket();
        ticket.setTicketId("1");
        ticket.setUserId(1);
        ticket.setPrice((float) 100);
        ticket.setStatus(Status.unused);
        ticket.setValidDate(new Date());
        ticketMapper.insert(ticket);

        ticket.setValidDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        ticket.setTicketId("2");
        ticketMapper.insert(ticket);

        ticket.setValidDate(new Date());
        ticket.setStatus(Status.used);
        ticket.setTicketId("3");
        ticketMapper.insert(ticket);

        Appointment appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setAppointmentId("1");
        appointment.setEventName("test");
        appointmentMapper.insert(appointment);
        System.out.println("start test");
    }

    @Test
    public void testAddEvents() throws ParseException{
        init();
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        event.setEventName("test-test");

        CommonReturnType response = managerController.addEvent(event);
        assertEquals("success", response.getStatus());
        assertEquals("test-test", eventMapper.selectByPrimaryKey("test-test").getEventName());
        eventMapper.deleteByPrimaryKey("test-test");
        finish();
    }

    @Test
    public void testDeleteEvents() throws ParseException{
        init();
        Event event = new Event();
        event.setEventName("test");

        CommonReturnType response = managerController.deleteEvent(event);
        assertEquals("success", response.getStatus());
        assertEquals(null, eventMapper.selectByPrimaryKey("test"));
        finish();
    }

    @Test
    public void updateEvents() throws ParseException{
        init();
        Event event = new Event();
        event.setEventName("test");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        event.setStartTime(format.parse("10:30:00"));
        event.setEndTime(format.parse("11:30:00"));
        event.setEventRemainPositions(10);
        event.setEventIntroduction("new game");
        event.setEventLocation("hall");

        CommonReturnType response = managerController.updateEvent(event);
        assertEquals("success", response.getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals(format.parse("10:30:00"), result.getStartTime());
        assertEquals(format.parse("11:30:00"), result.getEndTime());
        assertEquals(10, result.getEventRemainPositions());
        assertEquals("hall", result.getEventLocation());
        assertEquals("new game", result.getEventIntroduction());
        finish();
    }

    @Test
    public void testAddFacility() throws ParseException {
        init();
        Facility facility = new Facility();
        facility.setFacilityName("testFacility1");
        facility.setFacilityIntroduction("testFacility");
        facility.setFacilityOpenTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        facility.setFacilityCloseTime(new SimpleDateFormat("HH-mm-ss").parse("11-00-00"));
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setQueueStatus(100);
        CommonReturnType response = managerController.addFacility(facility);
        assertEquals("success", response.getStatus());
        assertEquals("testFacility1", facilityMapper.selectByPrimaryKey("testFacility1").getFacilityName());
        facilityMapper.deleteByPrimaryKey("testFacility1");
        finish();
    }

    @Test
    public void testDeleteFacility() throws ParseException {
        init();
        Facility facility = new Facility();
        facility.setFacilityName("testFacility");
        CommonReturnType response = managerController.deleteFacility(facility);
        assertEquals("success", response.getStatus());
        assertEquals(null, facilityMapper.selectByPrimaryKey("testFacility"));
        finish();
    }

    @Test
    public void testUpdateFacility() throws ParseException {
        init();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        Facility facility = new Facility();
        facility.setFacilityName("testFacility");
        facility.setFacilityIntroduction("test-test");
        facility.setFacilityOpenTime(format.parse("20-00-00"));
        facility.setFacilityCloseTime(format.parse("21-00-00"));
        facility.setFacilityStatus(FacilityStatus.repairing);
        facility.setQueueStatus(1000);

        CommonReturnType response = managerController.updateFacility(facility);
        assertEquals("success", response.getStatus());
        Facility result = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals(format.parse("20-00-00"), result.getFacilityOpenTime());
        assertEquals(format.parse("21-00-00"), result.getFacilityCloseTime());
        assertEquals("test-test", result.getFacilityIntroduction());
        assertEquals(FacilityStatus.repairing, result.getFacilityStatus());
        assertEquals(1000, result.getQueueStatus());
        finish();
    }

    @Test
    public void testCheckInTicket() throws ParseException {
        init();
        Ticket ticket = new Ticket();
        ticket.setTicketId("1");
        CommonReturnType response = managerController.checkTicket(ticket);
        assertEquals("success", response.getStatus());
        assertEquals(Status.used, ticketMapper.selectByPrimaryKey("1").getStatus());
        finish();
    }

    @Test
    public void testCheckAppointment() throws ParseException {
        init();
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        CommonReturnType response = managerController.checkAppointment(appointment);
        ErrorEnum errorEnum = (ErrorEnum) response.getData();
        assertEquals("success", response.getStatus());
        assertEquals(ErrorEnum.OK, errorEnum);
        finish();
    }


    public void finish(){
        eventMapper.deleteByPrimaryKey("test");
        facilityMapper.deleteByPrimaryKey("testFacility");
        ticketMapper.deleteByPrimaryKey("1");
        ticketMapper.deleteByPrimaryKey("2");
        ticketMapper.deleteByPrimaryKey("3");
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("end test");
    }
}
