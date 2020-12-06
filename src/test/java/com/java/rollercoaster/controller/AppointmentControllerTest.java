package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.*;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.AppointmentExample;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.response.CommonReturnType;
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

    private Event initEvent(int position, String eventName) throws ParseException {
        Event event = new Event();
        event.setEventRemainPositions(position);
        event.setEventName(eventName);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date startTime = sdf.parse("14:00");
        event.setStartTime(startTime);
        Date endTime = sdf.parse("15:00");
        event.setEndTime(endTime);
        eventMapper.insertSelective(event);
        return event;
    }
    @Test
    public void addAppointmentControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");
        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        CommonReturnType response = appointmentController.addAppointment(appointment);
        assertEquals("success", response.getStatus());
        String appointmentId = (String) response.getData();

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
    public void addAppointmentIsNullLoginControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        Event event = initEvent(10, "event test");
        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        CommonReturnType response = appointmentController.addAppointment(appointment);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
    }

    @Test
    public void addAppointmentIsFalseLoginControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        Event event = initEvent(10, "event test");
        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        CommonReturnType response = appointmentController.addAppointment(appointment);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
    }

    @Test
    public void addAppointmentNullLoginUserControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        Event event = initEvent(10, "event test");
        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        CommonReturnType response = appointmentController.addAppointment(appointment);
        assertEquals(ErrorEnum.USER_NOT_EXIST, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
    }

    @Test
    public void addAppointmentErrorImplControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event");
        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        CommonReturnType response = appointmentController.addAppointment(appointment);
        assertEquals(ErrorEnum.NO_SUCH_EVENT, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
    }

    @Test
    public void updateAppointmentControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");
        Event anotherEvent = initEvent(5, "event another");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentId("1");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        appointment.setEventName("event another");

        CommonReturnType response = appointmentController.updateAppointment(appointment);
        assertEquals("success", response.getStatus());
        assertEquals(4, eventMapper.selectByPrimaryKey("event another").getEventRemainPositions());
        assertEquals(11, eventMapper.selectByPrimaryKey("event test").getEventRemainPositions());
        assertEquals("event another",
                appointmentMapper.selectByPrimaryKey(appointment.getAppointmentId()).getEventName());
        assertEquals(userModel.getUserId(),
                appointmentMapper.selectByPrimaryKey(appointment.getAppointmentId()).getUserId());

        appointmentMapper.deleteByPrimaryKey(appointment.getAppointmentId());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        eventMapper.deleteByPrimaryKey(anotherEvent.getEventName());
    }

    @Test
    public void updateAppointmentIsNullLoginControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        Event event = initEvent(10, "event test");
        Event anotherEvent = initEvent(5, "event another");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentId("1");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        appointment.setEventName("event another");

        CommonReturnType response = appointmentController.updateAppointment(appointment);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());


        appointmentMapper.deleteByPrimaryKey(appointment.getAppointmentId());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        eventMapper.deleteByPrimaryKey(anotherEvent.getEventName());
    }

    @Test
    public void updateAppointmentFalseLoginControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        Event event = initEvent(10, "event test");
        Event anotherEvent = initEvent(5, "event another");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentId("1");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        appointment.setEventName("event another");

        CommonReturnType response = appointmentController.updateAppointment(appointment);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());


        appointmentMapper.deleteByPrimaryKey(appointment.getAppointmentId());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        eventMapper.deleteByPrimaryKey(anotherEvent.getEventName());
    }

    @Test
    public void deleteAppointmentIdTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentId("1");
        appointment.setValidDate(new Date());
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
    public void getAppointmentsControllerTest() throws BusinessException, ParseException {
        UserModel userModel = initUser();
        Event event = initEvent(10, "event test");

        Appointment appointment = new Appointment();
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setAppointmentId("1");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        CommonReturnType response = appointmentController.getAppointments();

        List<TimedAppointmentModel> timedAppointmentModelList = (List<TimedAppointmentModel>)response.getData();
        assertEquals("success", response.getStatus());
        assertEquals("1", timedAppointmentModelList.get(0).getAppointmentId());
        assertEquals(userModel.getUserId(), timedAppointmentModelList.get(0).getUserId());
        assertEquals("event test", timedAppointmentModelList.get(0).getEventName());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String startTimeGetBack = sdf.format(timedAppointmentModelList.get(0).getStartTime());
        String endTimeGetBack = sdf.format(timedAppointmentModelList.get(0).getEndTime());
        assertEquals("14:00", startTimeGetBack);
        assertEquals("15:00", endTimeGetBack);

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }
}