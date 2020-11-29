package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.service.model.MyCalendar;

import java.util.Date;
import java.util.List;

public interface StatisticCollectionService {
    int peopleInThatDay(MyCalendar myCalendar) throws BusinessException;

    int peopleInThatMonth(MyCalendar myCalendar) throws BusinessException;

    int peopleInThatYear(MyCalendar myCalendar) throws BusinessException;

    List<Date> whichDaysVisited(int userId);

    //List<String> top5Facility();

}
