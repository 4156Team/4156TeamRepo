package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;

public interface RatingService {

    ErrorEnum rateFacility(String facilityName, float rate);
}
