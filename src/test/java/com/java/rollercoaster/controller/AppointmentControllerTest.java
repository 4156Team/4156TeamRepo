package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.*;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.AppointmentExample;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AppointmentControllerTest {

    @Autowired
    private AppointmentController appointmentController;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;


    private UserModel initUser() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return userModel;
    }

    private Event initEvent(int position, String eventName) {
        Event event = new Event();
        event.setEventRemainPositions(position);
        //event.setEndTime();
        //event.setStartTime();
        event.setEventName(eventName);
        eventMapper.insertSelective(event);
        return event;
    }
    @Test
    public void addAppointmentControllerTest() throws BusinessException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");
        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");

        CommonReturnType response = appointmentController.addAppointment(appointment);
        assertEquals("success", response.getStatus());
        String appointmentId = (String) response.getData();

        Appointment appointmentGetBack = appointmentMapper.selectByPrimaryKey(appointmentId);
        assertEquals("event test", appointment.getEventName());
        assertEquals(userModel.getUserId(), appointment.getUserId());

        Event eventGetBack = eventMapper.selectByPrimaryKey("event test");
        assertEquals(9, eventGetBack.getEventRemainPositions());

        appointmentMapper.deleteByPrimaryKey(appointmentId);
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
    }

    @Test
    public void updateAppointmentControllerTest() throws BusinessException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");
        Event anotherEvent = initEvent(5, "event another");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentid("1");
        appointmentMapper.insertSelective(appointment);

        appointment.setEventName("event another");

        CommonReturnType response = appointmentController.updateAppointment(appointment);
        assertEquals("success", response.getStatus());
        assertEquals(4, eventMapper.selectByPrimaryKey("event another").getEventRemainPositions());
        assertEquals(11, eventMapper.selectByPrimaryKey("event test").getEventRemainPositions());
        assertEquals("event another",
                appointmentMapper.selectByPrimaryKey(appointment.getAppointmentid()).getEventName());
        assertEquals(userModel.getUserId(),
                appointmentMapper.selectByPrimaryKey(appointment.getAppointmentid()).getUserId());

        appointmentMapper.deleteByPrimaryKey(appointment.getAppointmentid());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        eventMapper.deleteByPrimaryKey(anotherEvent.getEventName());
    }

    @Test
    public void deleteAppointmentIdTest() throws BusinessException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentid("1");
        appointmentMapper.insertSelective(appointment);

        CommonReturnType response = appointmentController.deleteAppointmentId("1");
        assertEquals("success", response.getStatus());
        assertNull(appointmentMapper.selectByPrimaryKey("1"));
        assertEquals(11, eventMapper.selectByPrimaryKey("event test").getEventRemainPositions());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
    }

    @Test
    public void getAppointmentsControllerTest() throws BusinessException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentid("1");
        appointmentMapper.insertSelective(appointment);

        CommonReturnType response = appointmentController.getAppointments();

        assertEquals("success", response.getStatus());
        assertEquals("1", ((List<Appointment>)response.getData()).get(0).getAppointmentid());
        assertEquals(userModel.getUserId(), ((List<Appointment>)response.getData()).get(0).getUserId());
        assertEquals("event test", ((List<Appointment>)response.getData()).get(0).getEventName());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }
}