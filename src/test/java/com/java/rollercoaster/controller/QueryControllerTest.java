package com.java.rollercoaster.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.java.rollercoaster.service.model.EventModel;
import com.java.rollercoaster.service.model.FacilityModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TimeZone;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class QueryControllerTest {

    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private QueryController queryController;

    public void init() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Event event = new Event();
        event.setEventLocation("test location");
        event.setEventIntroduction("test introduction");
        event.setEventRemainPositions(1000);
        event.setStartTime(format.parse("10:30:00"));
        event.setEndTime(format.parse("11:00:00"));
        event.setEventName("testEvent");
        eventMapper.insert(event);

        Facility facility = new Facility();
        facility.setFacilityName("testFacility");
        facility.setFacilityIntroduction("test facility introduction");
        facility.setFacilityOpenTime(format.parse("11:30:00"));
        facility.setFacilityCloseTime(format.parse("12:00:00"));
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setQueueStatus(100);
        facilityMapper.insert(facility);

        System.out.println("start test");

    }

    @Test
    public void testQueryEventValid() throws ParseException {
        finish();
        init();
        CommonReturnType response = queryController.queryEvent("testEvent");
        EventModel rs = ((EventModel)response.getData());
        assertEquals("success", response.getStatus());
        assertEquals("test location", rs.getEventLocation());
        assertEquals("test introduction", rs.getEventIntroduction());
        assertEquals(10, rs.getStartTime().getHours());
        assertEquals(30, rs.getStartTime().getMinutes());
        assertEquals(0, rs.getStartTime().getSeconds());
        assertEquals(11, rs.getEndTime().getHours());
        assertEquals(0, rs.getEndTime().getMinutes());
        assertEquals(0, rs.getEndTime().getSeconds());
        assertEquals(1000, rs.getEventRemainPositions());
        finish();
    }

    @Test
    public void testQueryEventInvalid() throws ParseException {
        finish();
        init();
        CommonReturnType response = queryController.queryEvent("NonExistenceEvent");
        ErrorEnum rs = (ErrorEnum)response.getData();
        assertEquals("fail", response.getStatus());
        assertEquals(223, rs.getErrCode());
        assertEquals("The event does not exist", rs.getErrMsg());
        finish();
    }

    @Test
    public void testQueryFacilityValid() throws ParseException {
        finish();
        init();
        CommonReturnType response = queryController.queryFacility("testFacility");
        FacilityModel rs = ((FacilityModel)response.getData());
        assertEquals("success", response.getStatus());
        assertEquals("normal", rs.getFacilityStatus().toString());
        assertEquals("test facility introduction", rs.getFacilityIntroduction());
        assertEquals(11, rs.getFacilityOpenTime().getHours());
        assertEquals(30, rs.getFacilityOpenTime().getMinutes());
        assertEquals(0, rs.getFacilityOpenTime().getSeconds());
        assertEquals(12, rs.getFacilityCloseTime().getHours());
        assertEquals(0, rs.getFacilityCloseTime().getMinutes());
        assertEquals(0, rs.getFacilityCloseTime().getSeconds());
        assertEquals(100, rs.getQueueStatus());
        finish();
    }

    @Test
    public void testQueryFacilityInvalid() throws ParseException {
        finish();
        init();
        CommonReturnType response = queryController.queryFacility("NonExistenceFacility");
        ErrorEnum rs = (ErrorEnum)response.getData();
        assertEquals("fail", response.getStatus());
        assertEquals(226, rs.getErrCode());
        assertEquals("The facility does not exist", rs.getErrMsg());
        finish();
    }

    public void finish() throws ParseException{
        eventMapper.deleteByPrimaryKey("testEvent");
        facilityMapper.deleteByPrimaryKey("testFacility");
    }
}