package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.service.model.EventModel;
import com.java.rollercoaster.service.model.FacilityModel;

public interface QueryService {

    EventModel queryEvent(String eventName) throws BusinessException;

    FacilityModel queryFacility(String facilityName) throws BusinessException;
}
