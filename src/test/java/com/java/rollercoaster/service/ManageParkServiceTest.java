package com.java.rollercoaster.service;


import com.java.rollercoaster.dao.AnnouncementMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TypeMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.AnnouncementExample;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
class ManageParkServiceTest {
    @Autowired
    FacilityMapper facilityMapper;
    @Autowired
    EventMapper eventMapper;
    @Autowired
    ManageParkService manageParkService;
    @Autowired
    TypeMapper typeMapper;
    @Autowired
    AnnouncementMapper announcementMapper;

    @Test
    void addFacility() throws ParseException {
        Facility facility = new Facility();
        facility.setFacilityIntroduction("test");
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setFacilityOpenTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        facility.setFacilityCloseTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        facility.setQueueStatus(100);

        assertEquals(ErrorEnum.EMPTY_FACILITY_NAME, manageParkService.addFacility(facility));
        facility.setFacilityName("roller coaster");
        assertEquals(ErrorEnum.OK, manageParkService.addFacility(facility));
        assertEquals("roller coaster", facilityMapper.selectByPrimaryKey("roller coaster").getFacilityName());
        assertEquals(ErrorEnum.DUPLICATE_FACILITY_NAME, manageParkService.addFacility(facility));
        facilityMapper.deleteByPrimaryKey("roller coaster");
    }

    @Test
    void updateFacility() throws ParseException {
        Facility facility = new Facility();
        facility.setFacilityIntroduction("test");
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setFacilityOpenTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        facility.setFacilityCloseTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        facility.setQueueStatus(100);
        facility.setFacilityName("roller coaster");
        assertEquals(ErrorEnum.OK, manageParkService.addFacility(facility));

        Facility newFacility = new Facility();
        newFacility.setQueueStatus(200);
        assertEquals(ErrorEnum.EMPTY_FACILITY_NAME, manageParkService.updateFacility(newFacility));
        newFacility.setFacilityName("test");
        assertEquals(ErrorEnum.NO_SUCH_FACILITY, manageParkService.updateFacility(newFacility));
        newFacility.setFacilityName("roller coaster");
        assertEquals(ErrorEnum.OK, manageParkService.updateFacility(newFacility));
        assertEquals(200, facilityMapper.selectByPrimaryKey("roller coaster").getQueueStatus());
        facilityMapper.deleteByPrimaryKey("roller coaster");
    }


    @Test
    void deleteFacility() throws ParseException {
        Facility facility = new Facility();
        facility.setFacilityIntroduction("test");
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setFacilityOpenTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        facility.setFacilityCloseTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        facility.setQueueStatus(100);
        facility.setFacilityName("roller coaster");

        assertEquals(ErrorEnum.EMPTY_FACILITY_NAME, manageParkService.deleteFacility(null));

        assertEquals(ErrorEnum.OK, manageParkService.addFacility(facility));

        assertEquals(ErrorEnum.NO_SUCH_FACILITY, manageParkService.deleteFacility("test"));

        assertEquals(ErrorEnum.OK, manageParkService.deleteFacility("roller coaster"));

    }

    @Test
    void addEvent() throws ParseException {
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        event.setEventName(null);
        assertEquals(ErrorEnum.EMPTY_EVENT_NAME, manageParkService.addEvent(event));
        event.setEventName("testEvent");
        assertEquals(ErrorEnum.OK, manageParkService.addEvent(event));
        assertEquals("testEvent", eventMapper.selectByPrimaryKey("testEvent").getEventName());
        assertEquals(ErrorEnum.DUPLICATE_EVENT_NAME, manageParkService.addEvent(event));
        eventMapper.deleteByPrimaryKey("testEvent");
    }

    @Test
    void updateEvent() throws ParseException {
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setEventName("testEvent");
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        manageParkService.addEvent(event);
        Event newEvent = new Event();
        newEvent.setEventName(null);
        newEvent.setEventRemainPositions(100);
        assertEquals(ErrorEnum.EMPTY_EVENT_NAME, manageParkService.updateEvent(newEvent));
        newEvent.setEventName("ttttt");
        assertEquals(ErrorEnum.NO_SUCH_EVENT, manageParkService.updateEvent(newEvent));
        newEvent.setEventName("testEvent");
        assertEquals(ErrorEnum.OK, manageParkService.updateEvent(newEvent));
        assertEquals(100, eventMapper.selectByPrimaryKey("testEvent").getEventRemainPositions());
        eventMapper.deleteByPrimaryKey("testEvent");
    }

    @Test
    void deleteEvent() throws ParseException {
        Event event = new Event();
        event.setEventLocation("test");
        event.setEventIntroduction("test");
        event.setEventRemainPositions(1000);
        event.setEventName("testEvent");
        event.setStartTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        event.setEndTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        manageParkService.addEvent(event);
        assertEquals(ErrorEnum.EMPTY_EVENT_NAME, manageParkService.deleteEvent(null));
        assertEquals(ErrorEnum.NO_SUCH_EVENT, manageParkService.deleteEvent("tttt"));
        assertEquals(ErrorEnum.OK, manageParkService.deleteEvent("testEvent"));
    }


    @Test
    public void testGetTicketPrice() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        typeMapper.insert(type);

        assertEquals(50, manageParkService.getTicketPrice(TicketType.adult));

        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testChangeTicketPrice() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        typeMapper.insert(type);

        type.setTicketPrice(10f);
        assertEquals(ErrorEnum.OK, manageParkService.changeTicketPrice(type));
        assertEquals(10, typeMapper.selectByPrimaryKey(TicketType.adult).getTicketPrice());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testChangeTicketPriceFail() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        typeMapper.insert(type);

        type.setTicketPrice(null);
        assertEquals(ErrorEnum.EMPTY_TYPE_ATTRIBUTE, manageParkService.changeTicketPrice(type));
        assertEquals(50, typeMapper.selectByPrimaryKey(TicketType.adult).getTicketPrice());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testPushAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        assertEquals(ErrorEnum.OK, manageParkService.pushAnnouncement(announcement));
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andTextEqualTo("test Announcement");
        List<Announcement> announcements = announcementMapper.selectByExample(announcementExample);
        assertEquals(1, announcements.size());
        announcementMapper.deleteByExample(announcementExample);
    }

    @Test
    public void testPushAnnouncementFail() {
        Announcement announcement = new Announcement();
        announcement.setText("");
        announcement.setDate(new Date());
        assertEquals(ErrorEnum.EMPTY_ANNOUNCEMENT_ATTRIBUTE, manageParkService.pushAnnouncement(announcement));
    }

    @Test
    public void testGetAnnouncements() {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        announcementMapper.insert(announcement);
        int id = announcement.getAnnouncementId();
        announcement.setAnnouncementId(id);
        List<Announcement> announcementList = manageParkService.getAnnouncements();
        boolean flag = false;
        for (Announcement result : announcementList) {
            if (result.getText().equals(announcement.getText()) &&
            result.getAnnouncementId() == id) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
        announcementMapper.deleteByPrimaryKey(id);
    }
}