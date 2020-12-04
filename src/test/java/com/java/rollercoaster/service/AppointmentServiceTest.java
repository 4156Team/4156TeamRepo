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
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private UserModel initUser () throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);
        return userModel;
    }
    private Event initEvent(String name, int positions) {
        Event event = new Event();
        event.setEventRemainPositions(positions);
        //event.setEndTime();
        //event.setStartTime();
        event.setEventName(name);
        eventMapper.insertSelective(event);
        return event;
    }

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
        event.setEventName("event test");
        eventMapper.insertSelective(event);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        String addAppointmentReturn = appointmentService.addAppointment(appointment);
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
    public void addEmptyAppointmentTest() throws BusinessException {

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);

        try {
            appointmentService.addAppointment(null);
        } catch (BusinessException businessException) {
            assertEquals(251, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void addDuplicateAppointmentTest() throws BusinessException {

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentService.addAppointment(appointment);

        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(252, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void addWrongEventAppointmentTest() throws BusinessException {

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event");
        appointment.setValidDate(new Date());

        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(223, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void addWrongUserAppointmentTest() {

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userModel.setUserId(1);

        Event event = initEvent("event test", 10);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(20001, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void addBusyEventAppointmentTest() throws BusinessException {

        UserModel userModel = initUser();
        Event event = initEvent("event", 0);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event");
        appointment.setValidDate(new Date());

        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(253, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void updateAppointmentTest() throws BusinessException {
        System.out.println("updateAppointmentTest starts");

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);
        Event anotherEvent = initEvent("event another", 5);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
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
    public void updateNullAppointmentTest() throws BusinessException {
        UserModel userModel = initUser();

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(null);
        System.out.println(updateAppointmentReturn);

        assertEquals(251, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        appointmentMapper.deleteByPrimaryKey("1");

    }

    @Test
    public void updateNotExistAppointmentTest() throws BusinessException {

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);
        System.out.println(updateAppointmentReturn);

        assertEquals(254, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
    }

    @Test
    public void updateWrongEventAppointmentTest() throws BusinessException {

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);

        assertEquals(223, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
    }

    @Test
    public void updateWrongUserAppointmentTest() throws BusinessException {

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userModel.setUserId(1);
        Event event = initEvent("event test", 10);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);

        assertEquals(20001, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
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
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        ErrorEnum deleteAppointmentReturn = appointmentService.deleteAppointment(appointment.getAppointmentId(),userModel);
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
    public void appointmentsRecordsTest() throws BusinessException, ParseException {
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
        event.setEventName("event test");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date startTime = sdf.parse("14:00");
        event.setStartTime(startTime);
        Date endTime = sdf.parse("15:00");
        event.setEndTime(endTime);
        event.setEventLocation("test location");
        eventMapper.insertSelective(event);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);

        List<TimedAppointmentModel> timedAppointmentModelList = appointmentService.getAppointmentsByUserId(userModel.getUserId());
        assertEquals("event test", timedAppointmentModelList.get(0).getEventName());
        assertEquals("1", timedAppointmentModelList.get(0).getAppointmentId());
        assertEquals(userModel.getUserId(), timedAppointmentModelList.get(0).getUserId());
        String startTimeGetBack = sdf.format(timedAppointmentModelList.get(0).getStartTime());
        String endTimeGetBack = sdf.format(timedAppointmentModelList.get(0).getEndTime());
        assertEquals("14:00", startTimeGetBack);
        assertEquals("15:00", endTimeGetBack);

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("appointmentsRecordsTest ends");
    }

    @Test
    public void delete() {
        eventMapper.deleteByPrimaryKey("event test");
        appointmentMapper.deleteByPrimaryKey("1");
    }

}