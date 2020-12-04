package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.*;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.*;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.QuickPassService;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class QuickPassControllerTest {
    @Autowired
    private QuickPassController quickPassController;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private QuickPassService quickPassService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private QuickPassMapper quickPassMapper;

    private UserModel initUser() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Bob");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("78909809");
        userModel.setPassword("12345");
        userService.register(userModel);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return userModel;
    }

    private Facility initFacility() {
        Facility facility = new Facility();
        facility.setFacilityName("test facility");
        facilityMapper.insert(facility);
        return facility;
    }

    @Test
    public void addQuickPassTest() throws BusinessException, ParseException {
        facilityMapper.deleteByPrimaryKey("test facility");
        UserModel userModel = initUser();
        userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        System.out.println(userModel.getPhoneNumber());
        initFacility();
        QuickPass quickPass = new QuickPass();
        quickPass.setFacilityName("test facility");
        quickPass.setUserId(userModel.getUserId());
        quickPass.setStartTime(new Date());

        CommonReturnType response = quickPassController.addQuickPass(quickPass);
        System.out.println(response.getData().toString());
        assertEquals("success", response.getStatus());
        String quickPassId = (String) response.getData();

        assertEquals("test facility", quickPass.getFacilityName());
        assertEquals(userModel.getUserId(), quickPass.getUserId());

        quickPassMapper.deleteByPrimaryKey(quickPassId);
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void addQuickPassTest2() throws BusinessException, ParseException {
        facilityMapper.deleteByPrimaryKey("test facility");
        UserModel userModel = initUser();
        userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        System.out.println(userModel.getPhoneNumber());
        initFacility();
        QuickPass quickPass = new QuickPass();
        quickPass.setFacilityName("test facility");
        quickPass.setUserId(userModel.getUserId());
        quickPass.setStartTime(new Date());
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        //httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        CommonReturnType response = quickPassController.addQuickPass(quickPass);
        System.out.println(response.getData().toString());
        assertEquals(ErrorEnum.USER_NOT_LOGIN, (ErrorEnum)response.getData());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void addQuickPassTest3() throws BusinessException, ParseException {
        facilityMapper.deleteByPrimaryKey("test facility");
        UserModel userModel = initUser();
        userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        System.out.println(userModel.getPhoneNumber());
        initFacility();
        QuickPass quickPass = new QuickPass();
        quickPass.setFacilityName("test facility");
        quickPass.setUserId(userModel.getUserId());
        quickPass.setStartTime(new Date());
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        //httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        CommonReturnType response = quickPassController.addQuickPass(quickPass);
        System.out.println(response.getData().toString());
        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum)response.getData());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void addQuickPassTest4() throws BusinessException, ParseException {
        facilityMapper.deleteByPrimaryKey("test facility");
        UserModel userModel = initUser();
        userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        System.out.println(userModel.getPhoneNumber());
        initFacility();
        QuickPass quickPass = new QuickPass();
        quickPass.setFacilityName("facilitydoesn'texist");
        quickPass.setUserId(userModel.getUserId());
        quickPass.setStartTime(new Date());

        CommonReturnType response = quickPassController.addQuickPass(quickPass);
        System.out.println(response.getData().toString());
        assertEquals(ErrorEnum.NO_SUCH_FACILITY, (ErrorEnum)response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void deleteQuickPassTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        facilityMapper.deleteByPrimaryKey("test facility");
        Facility facility = initFacility();

        QuickPass quickPass = new QuickPass();
        quickPass.setUserId(userModel.getUserId());
        quickPass.setFacilityName("test facility");
        quickPass.setQuickpassId("1");
        quickPass.setStartTime(new Date());
        quickPassMapper.insertSelective(quickPass);

        CommonReturnType response = quickPassController.deleteQuickPass("1");
        System.out.println(response.getData().toString());
        assertEquals("success", response.getStatus());
        assertNull(quickPassMapper.selectByPrimaryKey("1"));

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void deleteQuickPassTest2() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        facilityMapper.deleteByPrimaryKey("test facility");
        Facility facility = initFacility();

        QuickPass quickPass = new QuickPass();
        quickPass.setUserId(userModel.getUserId());
        quickPass.setFacilityName("test facility");
        quickPass.setQuickpassId("1");
        quickPass.setStartTime(new Date());
        quickPassMapper.insertSelective(quickPass);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        CommonReturnType response = quickPassController.deleteQuickPass("1");
        System.out.println(response.getData().toString());
        assertEquals(ErrorEnum.USER_NOT_LOGIN, (ErrorEnum) response.getData());

        quickPassMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void deleteQuickPassTest3() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        facilityMapper.deleteByPrimaryKey("test facility");
        Facility facility = initFacility();

        QuickPass quickPass = new QuickPass();
        quickPass.setUserId(userModel.getUserId());
        quickPass.setFacilityName("test facility");
        quickPass.setQuickpassId("1");
        quickPass.setStartTime(new Date());
        quickPassMapper.insertSelective(quickPass);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        CommonReturnType response = quickPassController.deleteQuickPass("1");
        System.out.println(response.getData().toString());
        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum) response.getData());

        quickPassMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
    }

    @Test
    public void getQuickPassTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Facility facility = initFacility();

        QuickPass quickPass = new QuickPass();
        quickPass.setUserId(userModel.getUserId());
        quickPass.setFacilityName("test facility");
        quickPass.setQuickpassId("1");
        quickPass.setStartTime(new Date());
        quickPassMapper.insertSelective(quickPass);


        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);

        CommonReturnType response = quickPassController.getQuickPass();

        List<QuickPass> quickPassList = (List<QuickPass>)response.getData();
        assertEquals("success", response.getStatus());
        assertEquals("1", quickPassList.get(0).getQuickpassId());
        assertEquals(userModel.getUserId(), quickPassList.get(0).getUserId());
        assertEquals("test facility", quickPassList.get(0).getFacilityName());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
        quickPassMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void getQuickPassTest2() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Facility facility = initFacility();

        QuickPass quickPass = new QuickPass();
        quickPass.setUserId(userModel.getUserId());
        quickPass.setFacilityName("test facility");
        quickPass.setQuickpassId("1");
        quickPass.setStartTime(new Date());
        quickPassMapper.insertSelective(quickPass);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        CommonReturnType response = quickPassController.getQuickPass();

        assertEquals(ErrorEnum.USER_NOT_LOGIN, (ErrorEnum)response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
        quickPassMapper.deleteByPrimaryKey("1");

    }
    @Test
    public void getQuickPassTest3() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Facility facility = initFacility();

        QuickPass quickPass = new QuickPass();
        quickPass.setUserId(userModel.getUserId());
        quickPass.setFacilityName("test facility");
        quickPass.setQuickpassId("1");
        quickPass.setStartTime(new Date());
        quickPassMapper.insertSelective(quickPass);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        CommonReturnType response = quickPassController.getQuickPass();


        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum)response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        facilityMapper.deleteByPrimaryKey("test facility");
        quickPassMapper.deleteByPrimaryKey("1");

    }

}
