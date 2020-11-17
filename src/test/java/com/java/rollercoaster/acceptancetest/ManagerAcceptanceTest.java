package com.java.rollercoaster.acceptancetest;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.Status;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerAcceptanceTest {

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;

//    @BeforeEach
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

        Appointment appointment = new Appointment();
        appointment.setUserId(1);
        appointment.setAppointmentid("1");
        appointment.setEventName("test");
        appointmentMapper.insert(appointment);
        System.out.println("start test");
    }

    /**
     * Acceptance test for add Event.
     */
    @Test
    public void test1() throws ParseException {
        String url = "http://localhost:8080/manager/addEvent";
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        event.setEventName("test-test");
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        assertEquals("test-test", eventMapper.selectByPrimaryKey("test-test").getEventName());
        eventMapper.deleteByPrimaryKey("test-test");
    }

    /**
     * Acceptance test for delete event.
     */
    @Test
    public void test2() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/deleteEvent";
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, "test", CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        assertEquals(null, eventMapper.selectByPrimaryKey("test"));
        finish();
    }

    /**
     * Acceptance test for changing the event open and close time.
     */
    @Test
    public void test3() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/updateEvent";
        Event event = new Event();
        event.setEventName("test");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        event.setStartTime(format.parse("10:30:00"));
        event.setEndTime(format.parse("11:30:00"));
        System.out.println(event.getStartTime().getTime());
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals(format.parse("10:30:00"), result.getStartTime());
        assertEquals(format.parse("11:30:00"), result.getEndTime());
        finish();
    }

    /**
     * Acceptance Test for changing the remain number of appointments.
     */
    @Test
    public void test4() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/updateEvent";
        Event event = new Event();
        event.setEventName("test");
        event.setEventRemainPositions(10);

        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals(10, result.getEventRemainPositions());
        finish();
    }

    /**
     * Test for changing the introduction and eventLocation of event.
     */
    @Test
    public void test5() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/updateEvent";
        Event event = new Event();
        event.setEventName("test");
        event.setEventIntroduction("new game");
        event.setEventLocation("hall");

        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals("new game", result.getEventLocation());
        assertEquals("hall", result.getEventIntroduction());
        finish();
    }

    /**
     * Acceptance test for add Event.
     */
    @Test
    public void test6() throws ParseException {
        String url = "http://localhost:8080/manager/addEvent";
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        event.setEventName("test-test");
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        assertEquals("test-test", eventMapper.selectByPrimaryKey("test-test").getEventName());
        eventMapper.deleteByPrimaryKey("test-test");
    }

    /**
     * Acceptance test for delete event.
     */
    @Test
    public void test7() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/deleteEvent";
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, "test", CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        assertEquals(null, eventMapper.selectByPrimaryKey("test"));
        finish();
    }

    /**
     * Acceptance test for changing the event open and close time.
     */
    @Test
    public void test8() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/updateEvent";
        Event event = new Event();
        event.setEventName("test");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        event.setStartTime(format.parse("10:30:00"));
        event.setEndTime(format.parse("11:30:00"));
        System.out.println(event.getStartTime().getTime());
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals(format.parse("10:30:00"), result.getStartTime());
        assertEquals(format.parse("11:30:00"), result.getEndTime());
        finish();
    }

    /**
     * Acceptance Test for changing the remain number of appointments.
     */
    @Test
    public void test9() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/updateEvent";
        Event event = new Event();
        event.setEventName("test");
        event.setEventRemainPositions(10);

        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals(10, result.getEventRemainPositions());
        finish();
    }

    /**
     * Test for changing the introduction and eventLocation of event.
     */
    @Test
    public void test10() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/updateEvent";
        Event event = new Event();
        event.setEventName("test");
        event.setEventIntroduction("new game");
        event.setEventLocation("hall");

        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, event, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        Event result = eventMapper.selectByPrimaryKey("test");
        assertEquals("new game", result.getEventLocation());
        assertEquals("hall", result.getEventIntroduction());
        finish();
    }




    /**
     * Test for check in ticket successfully.
     */
    @Test
    public void test11() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/checkTicket";
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, "1", CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        assertEquals(Status.used, ticketMapper.selectByPrimaryKey("1").getStatus());
        finish();
    }

    /**
     * Test for check in ticket with wrong ticketId.
     */
    @Test
    public void test12() throws ParseException {
        init();
        String url = "http://localhost:8080/manager/checkTicket";
        ResponseEntity<CommonReturnType> response =
                restTemplate.postForEntity(url, "1", CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        assertEquals(Status.used, ticketMapper.selectByPrimaryKey("1").getStatus());
        finish();
    }


//    @AfterEach
    public void finish(){
        eventMapper.deleteByPrimaryKey("test");
        facilityMapper.deleteByPrimaryKey("testFacility");
        ticketMapper.deleteByPrimaryKey("1");
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("end test");
    }
}
