package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private FacilityMapper facilityMapper;

    @Override
    public ErrorEnum rateFacility(String facilityName, float rate) {
        if (null == facilityName) {
            return ErrorEnum.EMPTY_FACILITY_NAME;
        } else if (null == facilityMapper.selectByPrimaryKey(facilityName)) {
            return ErrorEnum.NO_SUCH_FACILITY;
        }
        Facility facility = facilityMapper.selectByPrimaryKey(facilityName);
        if (null == facility.getRating() || null == facility.getRatingPeople()) {
            facility.setRating(rate);
            facility.setRatingPeople(1);
        } else {
            facility.setRating((facility.getRating() * facility.getRatingPeople()
                    + rate) / (facility.getRatingPeople() + 1));
            facility.setRatingPeople(facility.getRatingPeople() + 1);
        }
        facilityMapper.updateByPrimaryKeySelective(facility);
        return ErrorEnum.OK;
    }


}
