package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.service.model.EventModel;
import com.java.rollercoaster.service.model.FacilityModel;
import java.util.List;

public interface QueryService {

    EventModel queryEvent(String eventName) throws BusinessException;

    FacilityModel queryFacility(String facilityName) throws BusinessException;

    List<EventModel> queryAllEvents() throws BusinessException;

    List<FacilityModel> queryAllFacilities() throws BusinessException;
}
