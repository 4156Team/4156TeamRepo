package com.java.rollercoaster.controller;


import com.java.rollercoaster.dao.CommentMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.service.CommentService;
import com.java.rollercoaster.service.RatingService;
import com.java.rollercoaster.service.model.CommentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class RatingControllerTest {
    @Autowired
    RatingService ratingService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    RatingController ratingController;
    @Autowired
    FacilityMapper facilityMapper;

    public void init() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Facility facility = new Facility();
        facility.setFacilityName("testFacility");
        facility.setFacilityIntroduction("test facility introduction");
        facility.setFacilityOpenTime(format.parse("11:30:00"));
        facility.setFacilityCloseTime(format.parse("12:00:00"));
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setQueueStatus(100);
        facility.setRating((float)3);
        facility.setRatingPeople(1);
        facilityMapper.insert(facility);

        System.out.println("start test");

    }

    @Test
    public void ratingTest() throws ParseException {
        finish();
        init();

        UserModel userModel = new UserModel();
        userModel.setUserName("Aria");
        userModel.setUserId(3);
        Facility facilityGot = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals((float)3, facilityGot.getRating());
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        ratingController.postRating("testFacility", "5" );
        facilityGot = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals((float)4, facilityGot.getRating());

        finish();

    }

    @Test
    public void ratingTest2() throws ParseException {
        finish();
        init();

        UserModel userModel = new UserModel();
        userModel.setUserName("Aria");
        userModel.setUserId(3);
        Facility facilityGot = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals((float)3, facilityGot.getRating());
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        facilityGot = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals((ErrorEnum)ratingController.postRating("testFacility", "5").getData(), ErrorEnum.USER_NOT_LOGIN);

        finish();

    }

    @Test
    public void ratingTest3() throws ParseException {
        finish();
        init();

        UserModel userModel = new UserModel();
        userModel.setUserName("Aria");
        userModel.setUserId(3);
        Facility facilityGot = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals((float)3, facilityGot.getRating());
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        facilityGot = facilityMapper.selectByPrimaryKey("testFacility");
        assertEquals((ErrorEnum)ratingController.postRating("testFacility", "5").getData(),
                ErrorEnum.USER_NOT_EXIST);

        finish();

    }
    public void finish() throws ParseException{
        facilityMapper.deleteByPrimaryKey("testFacility");
    }
}
