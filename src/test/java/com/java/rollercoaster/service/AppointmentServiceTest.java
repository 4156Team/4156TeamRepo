package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private EventMapper eventMapper;

    @Test
    public void addAppointmentTest() throws BusinessException {
        System.out.println("addAppointmentTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        Event event = new Event();
        event.setEventRemainPositions(10);
        //event.setEndTime();
        //event.setStartTime();
        event.setEventName("event test");
        eventMapper.insertSelective(event);

        Appointment appointment = new Appointment();
        appointment.setAppointmentid("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");


        ErrorEnum addAppointmentReturn = appointmentService.addAppointment(appointment);
        System.out.println(addAppointmentReturn);

        Appointment appointmentGetBack = appointmentMapper.selectByPrimaryKey("1");
        assertEquals(userModel.getUserId(), appointmentGetBack.getUserId());
        assertEquals("event test", appointmentGetBack.getEventName());

        Event eventGetBack = eventMapper.selectByPrimaryKey("event test");
        assertEquals(9, eventGetBack.getEventRemainPositions());


        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

        System.out.println("addAppointmentTest ends");
    }

    @Test
    public void updateAppointmentTest() throws BusinessException {
        System.out.println("updateAppointmentTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        Event event = new Event();
        event.setEventRemainPositions(10);
        //event.setEndTime();
        //event.setStartTime();
        event.setEventName("event test");
        eventMapper.insertSelective(event);

        Event anotherEvent = new Event();
        anotherEvent.setEventRemainPositions(5);
        //anotherEvent.setEndTime();
        //anotherEvent.setStartTime();
        anotherEvent.setEventName("event another");
        eventMapper.insertSelective(anotherEvent);

        Appointment appointment = new Appointment();
        appointment.setAppointmentid("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");

        appointmentMapper.insertSelective(appointment);

        appointment.setEventName("event another");

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);
        System.out.println(updateAppointmentReturn);

        Appointment appointmentGetBack = appointmentMapper.selectByPrimaryKey("1");
        assertEquals(userModel.getUserId(), appointmentGetBack.getUserId());
        assertEquals("event another", appointmentGetBack.getEventName());

        Event eventGetBack = eventMapper.selectByPrimaryKey("event test");
        assertEquals(11, eventGetBack.getEventRemainPositions());
        Event anotherEventGetBack = eventMapper.selectByPrimaryKey("event another");
        assertEquals(4, anotherEventGetBack.getEventRemainPositions());


        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        eventMapper.deleteByPrimaryKey(anotherEvent.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

        System.out.println("updateAppointmentTest ends");
    }

    @Test
    public void deleteAppointmentTest() throws BusinessException {
        System.out.println("deleteAppointmentTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        Event event = new Event();
        event.setEventRemainPositions(10);
        //event.setEndTime();
        //event.setStartTime();
        event.setEventName("event test");
        eventMapper.insertSelective(event);

        Appointment appointment = new Appointment();
        appointment.setAppointmentid("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");

        appointmentMapper.insertSelective(appointment);

        ErrorEnum deleteAppointmentReturn = appointmentService.deleteAppointment(appointment.getAppointmentid(),userModel);
        System.out.println(deleteAppointmentReturn);

        Appointment appointmentGetBack = appointmentMapper.selectByPrimaryKey("1");
        assertNull(appointmentGetBack);

        Event eventGetBack = eventMapper.selectByPrimaryKey("event test");
        assertEquals(11, eventGetBack.getEventRemainPositions());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());

        System.out.println("deleteAppointmentTest ends");
    }

    @Test
    public void appointmentsRecordsTest() throws BusinessException {
        System.out.println("appointmentsRecordsTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        Event event = new Event();
        event.setEventRemainPositions(10);
        //event.setEndTime();
        //event.setStartTime();
        event.setEventName("event test");
        eventMapper.insertSelective(event);

        Appointment appointment = new Appointment();
        appointment.setAppointmentid("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");

        appointmentMapper.insertSelective(appointment);

        List<Appointment> appointmentsList = appointmentService.getAppointmentsByUserId(userModel.getUserId());
        assertEquals("event test", appointmentsList.get(0).getEventName());
        assertEquals("1", appointmentsList.get(0).getAppointmentid());
        assertEquals(userModel.getUserId(), appointmentsList.get(0).getUserId());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("appointmentsRecordsTest ends");
    }

}