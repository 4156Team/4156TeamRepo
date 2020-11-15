package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.EventModel;
import com.java.rollercoaster.service.model.FacilityModel;
import org.junit.jupiter.api.Test;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.pojo.Event;
import org.junit.jupiter.api.Test;
import com.java.rollercoaster.service.impl.QueryServiceImpl;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class QueryServiceTest {

    @Autowired
    QueryService queryService;

    @Autowired
    EventMapper eventMapper;

    @Autowired
    FacilityMapper facilityMapper;

    @Test
    void queryEvent() {
        //test null input
        try{
            queryService.queryEvent(null);
        }
        catch (BusinessException e){
            assertEquals(e.getErrCode(),ErrorEnum.EMPTY_EVENT_NAME.getErrorCode());
        }

        //test event doesn't exist
        try{
            queryService.queryEvent("anNonexistentEvent");
        }
        catch (BusinessException e){
            assertEquals(e.getErrCode(),ErrorEnum.NO_SUCH_EVENT.getErrorCode());
        }

        //test a new event
        Event newEvent = new Event();
        newEvent.setEventName("queryTestEvent");
        newEvent.setStartTime(new Date(1,2,3,4,5,6));
        newEvent.setEndTime(new Date(1,1,1,7,8,9));
        newEvent.setEventIntroduction("test introduction");
        newEvent.setEventLocation("test location");


        eventMapper.insert(newEvent);
        EventModel eventQueried;
        try{
            eventQueried = queryService.queryEvent("queryTestEvent");
        }
        catch(BusinessException e){
            eventQueried = new EventModel();
        }
        assertEquals(eventQueried.getStartTime().getHours(),4);
        assertEquals(eventQueried.getStartTime().getMinutes(),5);
        assertEquals(eventQueried.getStartTime().getSeconds(),6);
        assertEquals(eventQueried.getEndTime().getHours(), 7);
        assertEquals(eventQueried.getEndTime().getMinutes(), 8);
        assertEquals(eventQueried.getEndTime().getSeconds(), 9);
        assertEquals(eventQueried.getEventIntroduction(), "test introduction");
        assertEquals(eventQueried.getEventLocation(), "test location");

    }

    @Test
    void queryFacility() {

        //test null input
        try{
            queryService.queryFacility(null);
        }
        catch (BusinessException e){
            assertEquals(e.getErrCode(),ErrorEnum.EMPTY_FACILITY_NAME.getErrorCode());
        }

        //test event doesn't exist
        try{
            queryService.queryFacility("anNonexistentFacility");
        }
        catch (BusinessException e){
            assertEquals(e.getErrCode(),ErrorEnum.NO_SUCH_FACILITY.getErrorCode());
        }

        //test a new event
        Facility newFacility = new Facility();
        newFacility.setFacilityName("queryTestFacility");
        newFacility.setFacilityOpenTime(new Date(1,2,3,4,5,6));
        newFacility.setFacilityCloseTime(new Date(1,1,1,7,8,9));
        newFacility.setFacilityIntroduction("test introduction");
        newFacility.setFacilityStatus(FacilityStatus.normal);


        facilityMapper.insert(newFacility);
        FacilityModel facilityQueried;
        try{
            facilityQueried = queryService.queryFacility("queryTestFacility");
        }
        catch(BusinessException e){
            facilityQueried = new FacilityModel();
        }
        assertEquals(facilityQueried.getFacilityOpenTime().getHours(),4);
        assertEquals(facilityQueried.getFacilityOpenTime().getMinutes(),5);
        assertEquals(facilityQueried.getFacilityOpenTime().getSeconds(),6);
        assertEquals(facilityQueried.getFacilityCloseTime().getHours(), 7);
        assertEquals(facilityQueried.getFacilityCloseTime().getMinutes(), 8);
        assertEquals(facilityQueried.getFacilityCloseTime().getSeconds(), 9);
        assertEquals(facilityQueried.getFacilityIntroduction(), "test introduction");
        assertEquals(facilityQueried.getFacilityStatus(), FacilityStatus.normal);
    }
}