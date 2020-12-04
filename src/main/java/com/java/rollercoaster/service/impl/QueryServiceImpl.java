package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.EventExample;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.FacilityExample;
import com.java.rollercoaster.service.QueryService;
import com.java.rollercoaster.service.model.EventModel;
import com.java.rollercoaster.service.model.FacilityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



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
        facility.setFacilityImage(facilityFromDb.getFacilityImage());
        facility.setRating(facilityFromDb.getRating());
        facility.setRatingPeople(facilityFromDb.getRatingPeople());
        return facility;
    }

    @Override
    public List<EventModel> queryAllEvents()  {
        List<Event> eventsFromDb = eventMapper.selectByExample(new EventExample());
        List<EventModel> eventsModel = new ArrayList<EventModel>();
        for (Event event : eventsFromDb) {
            EventModel eventModel = new EventModel();
            eventModel.setEventName(event.getEventName());
            eventModel.setEventIntroduction(event.getEventIntroduction());
            eventModel.setStartTime(event.getStartTime());
            eventModel.setEndTime(event.getEndTime());
            eventModel.setEventLocation(event.getEventLocation());
            eventModel.setEventRemainPositions(event.getEventRemainPositions());
            eventsModel.add(eventModel);
        }
        return eventsModel;
    }

    @Override
    public List<FacilityModel> queryAllFacilities() {
        List<Facility> facilitiesFromDb = facilityMapper.selectByExample(new FacilityExample());
        List<FacilityModel> facilitiesModel = new ArrayList<FacilityModel>();
        for (Facility facility : facilitiesFromDb) {
            FacilityModel facilityModel = new FacilityModel();
            facilityModel.setFacilityName(facility.getFacilityName());
            facilityModel.setFacilityIntroduction(facility.getFacilityIntroduction());
            facilityModel.setFacilityOpenTime(facility.getFacilityOpenTime());
            facilityModel.setFacilityCloseTime(facility.getFacilityCloseTime());
            facilityModel.setFacilityStatus(facility.getFacilityStatus());
            facilityModel.setQueueStatus(facility.getQueueStatus());
            facilityModel.setFacilityImage(facility.getFacilityImage());
            facilityModel.setRating(facility.getRating());
            facilityModel.setRatingPeople(facility.getRatingPeople());
            facilitiesModel.add(facilityModel);
        }
        return facilitiesModel;
    }
}
