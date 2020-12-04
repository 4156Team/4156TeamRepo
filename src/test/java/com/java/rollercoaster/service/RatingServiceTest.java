package com.java.rollercoaster.service;


import com.java.rollercoaster.dao.BalanceMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.QuickPassMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.*;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class RatingServiceTest {

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    RatingService ratingService;

    @Autowired
    FacilityMapper facilityMapper;

    @Autowired
    HttpServletRequest httpServletRequest;

    public void initFacility() {
        Facility newFacility = new Facility();
        newFacility.setFacilityName("quickpassTestFacility");
        newFacility.setFacilityOpenTime(new Date(1,2,3,4,5,6));
        newFacility.setFacilityCloseTime(new Date(1,1,1,7,8,9));
        newFacility.setFacilityIntroduction("test introduction");
        newFacility.setFacilityStatus(FacilityStatus.normal);

        facilityMapper.insert(newFacility);
    }

    public void removeFacility() {
        facilityMapper.deleteByPrimaryKey("quickpassTestFacility");
    }

    @Test
    public void rateTest0() throws BusinessException {
        removeFacility();
        initFacility();

        assertEquals(ratingService.rateFacility("quickpassTestFacility", 3), ErrorEnum.OK);
        removeFacility();
    }

    @Test
    public void rateTest1() throws BusinessException {
        removeFacility();
        assertEquals(ratingService.rateFacility("quickpassTestFacility", 3), ErrorEnum.NO_SUCH_FACILITY);
        removeFacility();
    }

    @Test
    public void rateTest2() throws BusinessException {
        removeFacility();
        assertEquals(ratingService.rateFacility(null, 3), ErrorEnum.EMPTY_FACILITY_NAME);
        removeFacility();
    }

    @Test
    public void rateTest3() throws BusinessException {
        removeFacility();
        initFacility();
        ratingService.rateFacility("quickpassTestFacility", 3);
        assertEquals(ratingService.rateFacility("quickpassTestFacility", 3), ErrorEnum.OK);
        removeFacility();
    }
}
