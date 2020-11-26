package com.java.rollercoaster.service.impl;

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
import com.java.rollercoaster.service.ManageParkService;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageParkServiceImpl implements ManageParkService {
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public ErrorEnum addEvent(Event event) {
        if (null == event.getEventName() || "".equals(event.getEventName())) {
            return ErrorEnum.EMPTY_EVENT_NAME;
        } else if (null != eventMapper.selectByPrimaryKey(event.getEventName())) {
            return ErrorEnum.DUPLICATE_EVENT_NAME;
        } else {
            eventMapper.insert(event);
            return ErrorEnum.OK;
        }
    }

    @Override
    public ErrorEnum updateEvent(Event event) {
        if (null == event.getEventName()) {
            return ErrorEnum.EMPTY_EVENT_NAME;
        } else if (null == eventMapper.selectByPrimaryKey(event.getEventName())) {
            return ErrorEnum.NO_SUCH_EVENT;
        }
        eventMapper.updateByPrimaryKeySelective(event);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum deleteEvent(String eventName) {
        if (null == eventName) {
            return ErrorEnum.EMPTY_EVENT_NAME;
        } else if (null == eventMapper.selectByPrimaryKey(eventName)) {
            return ErrorEnum.NO_SUCH_EVENT;
        }
        eventMapper.deleteByPrimaryKey(eventName);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum addFacility(Facility facility) {

        if (null == facility.getFacilityName() || "".equals(facility.getFacilityName())) {
            return ErrorEnum.EMPTY_FACILITY_NAME;
        } else if (null != facilityMapper.selectByPrimaryKey(facility.getFacilityName())) {
            return ErrorEnum.DUPLICATE_FACILITY_NAME;
        } else {
            facilityMapper.insert(facility);
            return ErrorEnum.OK;
        }
    }

    @Override
    public ErrorEnum updateFacility(Facility facility) {
        if (null == facility.getFacilityName()) {
            return ErrorEnum.EMPTY_FACILITY_NAME;
        } else if (null == facilityMapper.selectByPrimaryKey(facility.getFacilityName())) {
            return ErrorEnum.NO_SUCH_FACILITY;
        }
        facilityMapper.updateByPrimaryKeySelective(facility);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum deleteFacility(String facilityName) {
        if (null == facilityName) {
            return ErrorEnum.EMPTY_FACILITY_NAME;
        } else if (null == facilityMapper.selectByPrimaryKey(facilityName)) {
            return ErrorEnum.NO_SUCH_FACILITY;
        }
        facilityMapper.deleteByPrimaryKey(facilityName);
        return ErrorEnum.OK;
    }

}
