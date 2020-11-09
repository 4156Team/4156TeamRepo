package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.service.ManageParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageParkServiceImpl implements ManageParkService {
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public ErrorEnum addEvent(Event event) {
        eventMapper.insert(event);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum updateEvent(Event event) {
        eventMapper.updateByPrimaryKeySelective(event);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum deleteEvent(String eventName) {
        eventMapper.deleteByPrimaryKey(eventName);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum addFacility(Facility facility) {
        facilityMapper.insertSelective(facility);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum updateFacility(Facility facility) {
        facilityMapper.updateByPrimaryKeySelective(facility);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum deleteFacility(String facilityName) {
        facilityMapper.deleteByPrimaryKey(facilityName);
        return ErrorEnum.OK;
    }
}
