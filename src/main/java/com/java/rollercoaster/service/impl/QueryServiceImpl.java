package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.service.QueryService;
import com.java.rollercoaster.service.model.EventModel;
import com.java.rollercoaster.service.model.FacilityModel;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public EventModel queryEvent(String eventName) throws BusinessException {
        if (null == eventName) {
            throw new BusinessException(ErrorEnum.EMPTY_EVENT_NAME);
        } else if (null == eventMapper.selectByPrimaryKey(eventName)) {
            throw new BusinessException(ErrorEnum.NO_SUCH_EVENT);
        }
        Event eventFromDb = eventMapper.selectByPrimaryKey(eventName);
        EventModel event = new EventModel();
        event.setEventName(eventFromDb.getEventName());
        event.setEventIntroduction(eventFromDb.getEventIntroduction());
        event.setStartTime(eventFromDb.getStartTime());
        event.setEndTime(eventFromDb.getEndTime());
        event.setEventLocation(eventFromDb.getEventLocation());
        event.setEventRemainPositions(eventFromDb.getEventRemainPositions());
        return event;
    }

    @Override
    public FacilityModel queryFacility(String facilityName) throws BusinessException {
        if (null == facilityName) {
            throw new BusinessException(ErrorEnum.EMPTY_FACILITY_NAME);
        } else if (null == facilityMapper.selectByPrimaryKey(facilityName)) {
            throw new BusinessException(ErrorEnum.NO_SUCH_FACILITY);
        }
        Facility facilityFromDb = facilityMapper.selectByPrimaryKey(facilityName);
        FacilityModel facility = new FacilityModel();
        facility.setFacilityName(facilityFromDb.getFacilityName());
        facility.setFacilityIntroduction(facilityFromDb.getFacilityIntroduction());
        facility.setFacilityOpenTime(facilityFromDb.getFacilityOpenTime());
        facility.setFacilityCloseTime(facilityFromDb.getFacilityCloseTime());
        facility.setFacilityStatus(facilityFromDb.getFacilityStatus());
        facility.setQueueStatus(facilityFromDb.getQueueStatus());
        return facility;
    }
}
