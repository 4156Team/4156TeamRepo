package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;

public interface ManageParkService {
    ErrorEnum addFacility(Facility facility);
    ErrorEnum updateFacility(Facility facility);
    //update的时候对于传入的是null的属性不进行修改
    ErrorEnum deleteFacility(String facilityName);
    ErrorEnum addEvent(Event event);
    ErrorEnum updateEvent(Event event);
    ErrorEnum deleteEvent(String eventName);
}
