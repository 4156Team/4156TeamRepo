package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.TicketExample;
import com.java.rollercoaster.service.QueryService;
import com.java.rollercoaster.service.StatisticCollectionService;
import com.java.rollercoaster.service.model.FacilityModel;
import com.java.rollercoaster.service.model.MyCalendar;
import com.java.rollercoaster.service.model.enumeration.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticCollectionServiceImpl implements StatisticCollectionService {
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private QueryService queryService;

    @Override
    public int peopleInThatDay(MyCalendar myCalendar) throws BusinessException {
        if (null == myCalendar.getDay()
                || null == myCalendar.getMonth()
                || null == myCalendar.getYear()) {
            throw new BusinessException(ErrorEnum.EMPTY_DATE_ATTRIBUTE);
        }
        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        Calendar calendar = Calendar.getInstance();
        calendar.set(myCalendar.getYear(), myCalendar.getMonth() - 1,
                myCalendar.getDay());
        if (calendar.getTime().getTime() > new Date().getTime()) {
            throw new BusinessException(ErrorEnum.TIME_OVER_CURRENT_DAY);
        }
        criteria.andValidDateEqualTo(calendar.getTime());
        criteria.andStatusEqualTo(Status.used);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        return ticketList.size();
    }

    @Override
    public int peopleInThatMonth(MyCalendar myCalendar) throws BusinessException {
        if (null == myCalendar.getMonth()
                || null == myCalendar.getYear()) {
            throw new BusinessException(ErrorEnum.EMPTY_DATE_ATTRIBUTE);
        }
        List<Ticket> ticketList = ticketMapper.selectByExample(new TicketExample());
        int year = myCalendar.getYear();
        int month = myCalendar.getMonth() - 1;
        int count = 0;
        for (Ticket ticket : ticketList) {
            Calendar curCalendar = Calendar.getInstance();
            curCalendar.setTime(ticket.getValidDate());
            int curYear = curCalendar.get(Calendar.YEAR);
            int curMonth = curCalendar.get(Calendar.MONTH);
            if (ticket.getStatus() == Status.used
                    && curYear == year && curMonth == month) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public int peopleInThatYear(MyCalendar myCalendar) throws BusinessException {
        if (null == myCalendar.getYear()) {
            throw new BusinessException(ErrorEnum.EMPTY_DATE_ATTRIBUTE);
        }
        List<Ticket> ticketList = ticketMapper.selectByExample(new TicketExample());
        int year = myCalendar.getYear();
        int count = 0;
        for (Ticket ticket : ticketList) {
            Calendar curCalendar = Calendar.getInstance();
            curCalendar.setTime(ticket.getValidDate());
            int curYear = curCalendar.get(Calendar.YEAR);
            if (ticket.getStatus() == Status.used
                    && curYear == year) {
                count += 1;
            }
        }
        return count;
    }

    @Override
    public List<Date> whichDaysVisited(int userId) {
        List<Date> dates = new ArrayList<>();
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andStatusEqualTo(Status.used)
                .andUserIdEqualTo(userId);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        for (Ticket ticket : ticketList) {
            dates.add(ticket.getValidDate());
        }
        return dates;
    }

    @Override
    public List<FacilityModel> top5Facility() throws BusinessException {
        List<FacilityModel> facilities = queryService.queryAllFacilities();
        facilities.sort((o1, o2) ->
                o1.getRating() > o2.getRating() ? -1 : 1);
        List<FacilityModel> result = new ArrayList<>();
        for (int i = 0; i < Math.min(5, facilities.size()); i++) {
            result.add(facilities.get(i));
        }
        return result;
    }
}
