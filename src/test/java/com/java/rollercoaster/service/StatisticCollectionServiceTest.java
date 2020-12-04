package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.model.FacilityModel;
import com.java.rollercoaster.service.model.MyCalendar;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StatisticCollectionServiceTest {
    @Autowired
    private StatisticCollectionService statisticCollectionService;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private FacilityMapper facilityMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

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

    public void init() throws ParseException {
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

    public void finish() {
        ticketMapper.deleteByPrimaryKey("1111");
        ticketMapper.deleteByPrimaryKey("1221");
        ticketMapper.deleteByPrimaryKey("1331");
        ticketMapper.deleteByPrimaryKey("1441");
        ticketMapper.deleteByPrimaryKey("1551");
        ticketMapper.deleteByPrimaryKey("1661");
    }

    @Test
    public void testPeopleInThatDayFail() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setMonth(10);
        myCalendar.setDay(5);
        try {
            statisticCollectionService.peopleInThatDay(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatDayFail2() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        myCalendar.setDay(20);
        try {
            statisticCollectionService.peopleInThatDay(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatDayFail3() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        myCalendar.setMonth(10);
        try {
            statisticCollectionService.peopleInThatDay(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatDayFail4() throws ParseException {
        init();
        Calendar calendar = Calendar.getInstance();

        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(calendar.get(Calendar.YEAR));
        myCalendar.setMonth(calendar.get(Calendar.MONTH) + 1);
        myCalendar.setDay(calendar.get(Calendar.DATE) + 8);
        try {
            statisticCollectionService.peopleInThatDay(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.TIME_OVER_CURRENT_DAY, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatDayNormal() throws ParseException, BusinessException {
        init();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        calendar.set(year, month, day - 1);
        ticketMapper.insert(createTicket("2111",1, Status.used, 100,
                sdf.format(calendar.getTime()), TicketType.adult));

        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(year);
        myCalendar.setMonth(month + 1);
        myCalendar.setDay(day - 1);
        int count = statisticCollectionService.peopleInThatDay(myCalendar);
        assertEquals(1, count);
        ticketMapper.deleteByPrimaryKey("2111");
        finish();
    }

    @Test
    public void testPeopleInThatMonthFail() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setMonth(10);
        try {
            statisticCollectionService.peopleInThatMonth(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatMonthFail2() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        try {
            statisticCollectionService.peopleInThatMonth(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatMonthFail3() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        myCalendar.setMonth(12);
        try {
            statisticCollectionService.peopleInThatMonth(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.TIME_OVER_CURRENT_MONTH, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatMonthNormal() throws ParseException, BusinessException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        myCalendar.setMonth(11);
        int count = statisticCollectionService.peopleInThatMonth(myCalendar);
        assertEquals(2, count);
        finish();
    }

    @Test
    public void testPeopleInThatYearFail() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        try {
            statisticCollectionService.peopleInThatYear(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_DATE_ATTRIBUTE, businessException.getCommonError());
        }
        finish();
    }

    @Test
    public void testPeopleInThatYearFail2() throws ParseException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2020);
        try {
            statisticCollectionService.peopleInThatYear(myCalendar);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.TIME_OVER_CURRENT_YEAR, businessException.getCommonError());
        }
        finish();
    }




    @Test
    public void testPeopleInThatYearNormal() throws ParseException, BusinessException {
        init();
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setYear(2019);
        int count = statisticCollectionService.peopleInThatYear(myCalendar);
        assertEquals(1, count);
        finish();
    }

    @Test
    public void testWhichDaysVisited() throws ParseException {
        init();
        List<Date> dates = new ArrayList<>();
        dates.add(sdf.parse("2019-08-24"));
        List<Date> result = statisticCollectionService.whichDaysVisited(3);
        assertEquals(true, result.containsAll(dates));
        assertEquals(1, result.size());
        finish();
    }

    @Test
    public void testGetTop5Facility() throws BusinessException, ParseException {
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

        List<FacilityModel> result = statisticCollectionService.top5Facility();
        assertEquals(5.1f, result.get(0).getRating());

        facilityMapper.deleteByPrimaryKey("roller coaster");

    }
}
