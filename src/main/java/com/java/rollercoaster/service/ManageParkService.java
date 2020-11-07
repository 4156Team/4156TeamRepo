package com.java.rollercoaster.service;

import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;

public interface ManageParkService {
    Error addFacility(Facility facility);
    Error updateFacility(Facility facility);
    //update的时候对于传入的是null的属性不进行修改
    Error deleteFacility(String facilityName);
    Error addEvent(Event event);
    Error updateEvent(Event event);
    Error deleteEvent(String eventName);
}
