package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.AnnouncementMapper;
import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.QuickPassMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.TypeMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.AnnouncementExample;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AnnouncementService;
import com.java.rollercoaster.service.TicketPriceService;
import com.java.rollercoaster.service.model.FacilityModel;
import com.java.rollercoaster.service.model.MyCalendar;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private QuickPassMapper quickPassMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

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

        Appointment appointment1 = new Appointment();
        appointment1.setUserId(1);
        appointment1.setAppointmentId("1");
        appointment1.setEventName("test");
        appointment1.setValidDate(new Date());
        appointmentMapper.insert(appointment1);

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
        assertEquals("20-00-00", format.format(result.getFacilityOpenTime()));
        assertEquals("21-00-00", format.format(result.getFacilityCloseTime()));
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


    @Test
    public void testPeopleInThatDayFail() throws ParseException {
        init1();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setMonth(10);
        myCalendar.setDay(5);
        CommonReturnType response = managerController.peopleInThatDay(myCalendar);
        assertEquals("fail", response.getStatus());
        assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, response.getData());
        finish1();
    }

    @Test
    public void testPeopleInThatMonthFail() throws ParseException {
        init1();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setMonth(10);
        CommonReturnType response = managerController.peopleInThatMonth(myCalendar);
        assertEquals("fail", response.getStatus());
        assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, response.getData());
        finish1();
    }

    @Test
    public void testPeopleInThatYearFail() throws ParseException {
        init1();
        MyCalendar myCalendar = new MyCalendar();
        CommonReturnType response = managerController.peopleInThatYear(myCalendar);
        assertEquals("fail", response.getStatus());
        assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, response.getData());
        finish1();
    }

    @Test
    public void testPeopleInThatDayNormal() throws ParseException {
        init1();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        myCalendar.setMonth(11);
        myCalendar.setDay(25);
        CommonReturnType response = managerController.peopleInThatDay(myCalendar);
        assertEquals(1, response.getData());
        assertEquals("success", response.getStatus());
        finish1();
    }

    @Test
    public void testPeopleInThatMonthNormal() throws ParseException {
        init1();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        myCalendar.setMonth(11);
        CommonReturnType response = managerController.peopleInThatMonth(myCalendar);
        assertEquals(2, response.getData());
        assertEquals("success", response.getStatus());
        finish1();
    }

    @Test
    public void testPeopleInThatYearNormal() throws ParseException {
        init1();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2019);
        CommonReturnType response = managerController.peopleInThatYear(myCalendar);
        assertEquals(1, response.getData());
        assertEquals("success", response.getStatus());
        finish1();
    }

    @Test
    public void testWhichDaysVisited() throws ParseException {
        init1();
        List<Date> dates = new ArrayList<>();
        dates.add(sdf.parse("2019-08-24"));
        CommonReturnType response = managerController.whichDaysVisited(3);
        List<Date> result = (List<Date>) response.getData();
        assertEquals("success", response.getStatus());
        assertEquals(true, result.containsAll(dates));
        assertEquals(1, result.size());
        finish1();
    }


    private Ticket createTicket(String id, int userId, Status status, float price,
                                String validDate, TicketType ticketType) throws ParseException {
        Ticket ticket = new Ticket();
        ticket.setTicketId(id);
        ticket.setUserId(userId);
        ticket.setStatus(status);
        ticket.setPrice(price);
        ticket.setValidDate(sdf.parse(validDate));
        ticket.setTicketType(ticketType);
        return ticket;
    }


    public void init1() throws ParseException {
        ticketMapper.insert(createTicket("1441",1, Status.used, 100,
                "2020-11-01", TicketType.adult));
        ticketMapper.insert(createTicket("1111",1, Status.used, 100,
                "2020-11-25", TicketType.adult));
        ticketMapper.insert(createTicket("1551",1, Status.used, 100,
                "2020-08-01", TicketType.adult));

        ticketMapper.insert(createTicket("1221",2, Status.unused, 100,
                "2020-11-25", TicketType.adult));
        ticketMapper.insert(createTicket("1331",3, Status.unused, 100,
                "2019-11-23", TicketType.adult));
        ticketMapper.insert(createTicket("1661",3, Status.used, 100,
                "2019-08-24", TicketType.adult));
    }

    public void finish1() {
        ticketMapper.deleteByPrimaryKey("1111");
        ticketMapper.deleteByPrimaryKey("1221");
        ticketMapper.deleteByPrimaryKey("1331");
        ticketMapper.deleteByPrimaryKey("1441");
        ticketMapper.deleteByPrimaryKey("1551");
        ticketMapper.deleteByPrimaryKey("1661");
    }


    @Test
    public void testPushAnnouncement() throws BusinessException, UnirestException {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        CommonReturnType response = managerController.pushAnnouncement(announcement);
        assertEquals("success", response.getStatus());
        assertEquals(ErrorEnum.OK, response.getData());
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andTextEqualTo("test Announcement");
        List<Announcement> announcements = announcementMapper.selectByExample(announcementExample);
        assertEquals(1, announcements.size());
        announcementMapper.deleteByExample(announcementExample);
    }

    @Test
    public void testChangeTicketPrice() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        if (null != typeMapper.selectByPrimaryKey(type.getTicketType())){
            typeMapper.updateByPrimaryKey(type);
        } else {
            typeMapper.insert(type);
        }

        type.setTicketPrice(10f);
        CommonReturnType response = managerController.changeTicketPrice(type);
        assertEquals("success", response.getStatus());
        assertEquals(ErrorEnum.OK, response.getData());
        assertEquals(10, typeMapper.selectByPrimaryKey(TicketType.adult).getTicketPrice());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testDeleteAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        announcementMapper.insert(announcement);
        CommonReturnType response = managerController.deleteAnnouncement(announcement);
        assertEquals(ErrorEnum.OK, response.getData());
    }

    @Test
    public void testTop5Facility() throws BusinessException, ParseException {
        Facility facility = new Facility();
        facility.setFacilityIntroduction("test");
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setFacilityOpenTime(new SimpleDateFormat("HH-mm-ss").parse("10-00-00"));
        facility.setFacilityCloseTime(new SimpleDateFormat("HH-mm-ss").parse("19-00-00"));
        facility.setQueueStatus(100);
        facility.setFacilityName("roller coaster");
        facility.setRating(5.1f);
        facility.setRatingPeople(100);
        facilityMapper.insert(facility);

        CommonReturnType response = managerController.top5Facility();
        assertEquals("success", response.getStatus());
        assertEquals(5.1f, ((List< FacilityModel >) response.getData()).get(0).getRating());

        facilityMapper.deleteByPrimaryKey("roller coaster");

    }


    @Test
    public void testCheckQuickPass() {
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("1000100");
        quickPass.setUserId(10000);
        quickPass.setStartTime(new Date(new Date().getTime() - 10000));
        quickPass.setFacilityName("Hunny Pot Spin");
        quickPassMapper.insertSelective(quickPass);
        CommonReturnType commonReturnType = managerController.checkQuickPass(quickPass);
        assertEquals(ErrorEnum.OK, commonReturnType.getData());
        assertEquals("success", commonReturnType.getStatus());
        quickPassMapper.deleteByPrimaryKey("1000100");
    }

}
