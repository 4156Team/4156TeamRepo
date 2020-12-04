package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.AppointmentMapper;
import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.*;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        ErrorEnum registerReturn =  userService.register(userModel);
        System.out.println(registerReturn);
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
        ErrorEnum registerReturn =  userService.register(userModel);
        System.out.println(registerReturn);

        Event event = new Event();
        event.setEventRemainPositions(10);
        event.setEventName("event test");
        eventMapper.insertSelective(event);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointment.setUserId(userModel.getUserId());
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);
        appointment.setUserId(userAccount.get(0).getUserId());
        appointmentService.addAppointment(appointment);

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

        System.out.println("addEmptyAppointmentTest starts");

        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);
        System.out.println("userId from userModel: " + userModel.getUserId());

        try {
            appointmentService.addAppointment(null);
        } catch (BusinessException businessException) {
            assertEquals(251, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("addEmptyAppointmentTest ends");

    }

    @Test
    public void addDuplicateAppointmentTest() throws BusinessException {
        System.out.println("addDuplicateAppointmentTest starts");
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        ErrorEnum registerReturn =  userService.register(userModel);
        System.out.println(registerReturn);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Event event = initEvent("event test", 10);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userAccount.get(0).getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());
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
        System.out.println("addDuplicateAppointmentTest ends");
    }

    @Test
    public void addWrongEventAppointmentTest() throws BusinessException {
        System.out.println("addWrongEventAppointmentTest starts");
        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event");
        appointment.setValidDate(new Date());
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(223, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("addWrongEventAppointmentTest ends");
    }

    @Test
    public void addWrongUserAppointmentTest() {
        System.out.println("addWrongUserAppointmentTest starts");
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
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());
        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(20001, businessException.getErrCode());
        }

        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("addWrongUserAppointmentTest ends");

    }

    @Test
    public void addBusyEventAppointmentTest() throws BusinessException {
        System.out.println("addBusyEventAppointmentTest starts");
        UserModel userModel = initUser();
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Event event = initEvent("event", 0);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userAccount.get(0).getUserId());
        appointment.setEventName("event");
        appointment.setValidDate(new Date());
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        try {
            appointmentService.addAppointment(appointment);
        } catch (BusinessException businessException) {
            assertEquals(253, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
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
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

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
        System.out.println("updateNullAppointmentTest starts");
        UserModel userModel = initUser();
        System.out.println("userId from userModel: " + userModel.getUserId());

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(null);
        System.out.println(updateAppointmentReturn);

        assertEquals(251, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("updateNullAppointmentTest ends");

    }

    @Test
    public void updateNotExistAppointmentTest() throws BusinessException {
        System.out.println("updateNotExistAppointmentTest starts");
        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);
        System.out.println(updateAppointmentReturn);

        assertEquals(254, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("updateNotExistAppointmentTest ends");
    }

    @Test
    public void updateWrongEventAppointmentTest() throws BusinessException {
        System.out.println("updateWrongEventAppointmentTest starts");
        UserModel userModel = initUser();
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);
        Event event = initEvent("event test", 10);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userAccount.get(0).getUserId());
        appointment.setEventName("event");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);

        assertEquals(223, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("updateWrongEventAppointmentTest ends");
    }

    @Test
    public void updateWrongUserAppointmentTest() throws BusinessException {
        System.out.println("updateWrongUserAppointmentTest starts");
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
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);

        assertEquals(20001, updateAppointmentReturn.getErrCode());

        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("updateWrongUserAppointmentTest ends");
    }

    @Test
    public void updateSameEventAppointmentTest() throws BusinessException, ParseException {
        System.out.println("updateSameEventAppointmentTest starts");
        UserModel userModel = initUser();
        Event event = initEvent("event test", 10);
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userAccount.get(0).getUserId());
        appointment.setEventName("event test");
        Date now = new Date();
        appointment.setValidDate(now);
        appointmentService.addAppointment(appointment);
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        Calendar c = Calendar.getInstance();
        c.setTime(now);
        System.out.println("Today is " + now);
        c.add(Calendar.DAY_OF_MONTH, 1);//add one day to today
        Date tomorrow = c.getTime();

        appointment.setValidDate(tomorrow);
        System.out.println("Tomorrow is " + tomorrow);
        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);

        assertEquals(100, updateAppointmentReturn.getErrCode());
        assertEquals(9, eventMapper.selectByPrimaryKey("event test").getEventRemainPositions());
        System.out.println(appointmentMapper.selectByPrimaryKey("1").getValidDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateGetBack = sdf.parse(sdf.format(appointmentMapper.selectByPrimaryKey("1").getValidDate()));
        tomorrow = sdf.parse(sdf.format(tomorrow));

        assertEquals(tomorrow, dateGetBack);

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("updateSameEventAppointmentTest ends");
    }

    @Test
    public void updateFullEventAppointmentTest() throws BusinessException, ParseException {
        System.out.println("updateFullEventAppointmentTest starts");
        UserModel userModel = initUser();
        Event event = initEvent("event test", 1);
        Event anotherEvent = initEvent("another", 0);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userAccount.get(0).getUserId());
        appointment.setEventName("event test");
        Date now = new Date();
        appointment.setValidDate(now);
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());
        appointmentService.addAppointment(appointment);


        appointment.setEventName("another");
        ErrorEnum updateAppointmentReturn = appointmentService.updateAppointment(appointment);

        assertEquals(253, updateAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        eventMapper.deleteByPrimaryKey(anotherEvent.getEventName());
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("updateFullEventAppointmentTest ends");
    }
    @Test
    public void deleteAppointmentTest() throws BusinessException {
        System.out.println("deleteAppointmentTest starts");
        UserModel userModel = initUser();

        Event event = initEvent("event test", 10);

        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

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
    public void deleteNullIdAppointmentTest() throws BusinessException {
        System.out.println("deleteNullIdAppointmentTest starts");
        UserModel userModel = initUser();
        System.out.println("userId from userModel: " + userModel.getUserId());

        Event event = initEvent("event test", 10);

        ErrorEnum deleteAppointmentReturn = appointmentService.deleteAppointment(null,userModel);
        assertEquals(251, deleteAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        System.out.println("deleteNullIdAppointmentTest ends");
    }

    @Test
    public void deleteNotExistAppointmentTest() throws BusinessException {
        System.out.println("deleteNotExistAppointmentTest starts");
        UserModel userModel = initUser();
        System.out.println("userId from userModel: " + userModel.getUserId());

        Event event = initEvent("event test", 10);

        ErrorEnum deleteAppointmentReturn = appointmentService.deleteAppointment("1",userModel);
        assertEquals(254, deleteAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey(event.getEventName());
        System.out.println("deleteNotExistAppointmentTest ends");
    }

    @Test
    public void deleteNotSameUserAppointmentTest() throws BusinessException {
        System.out.println("deleteNotSameUserAppointmentTest starts");
        UserModel userModel = initUser();

        Event event = initEvent("event test", 10);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userModel.getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        userModel.setUserId(userModel.getUserId() - 1);

        ErrorEnum deleteAppointmentReturn = appointmentService.deleteAppointment("1",userModel);
        assertEquals(255, deleteAppointmentReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId() + 1);
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId() + 1);
        appointmentMapper.deleteByPrimaryKey("1");
        eventMapper.deleteByPrimaryKey(event.getEventName());
        System.out.println("deleteNotSameUserAppointmentTest ends");
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
        ErrorEnum registerReturn =  userService.register(userModel);
        System.out.println(registerReturn);

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

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);
        Appointment appointment = new Appointment();
        appointment.setAppointmentId("1");
        appointment.setUserId(userAccount.get(0).getUserId());
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        appointmentMapper.insertSelective(appointment);
        System.out.println("userId from userModel: " + userModel.getUserId());
        System.out.println("userId from appointment: " + appointment.getUserId());

        List<TimedAppointmentModel> timedAppointmentModelList = appointmentService.getAppointmentsByUserId(userAccount.get(0).getUserId());
        assertEquals("event test", timedAppointmentModelList.get(0).getEventName());
        assertEquals("1", timedAppointmentModelList.get(0).getAppointmentId());
        assertEquals(userAccount.get(0).getUserId(), timedAppointmentModelList.get(0).getUserId());
        String startTimeGetBack = sdf.format(timedAppointmentModelList.get(0).getStartTime());
        String endTimeGetBack = sdf.format(timedAppointmentModelList.get(0).getEndTime());
        assertEquals("14:00", startTimeGetBack);
        assertEquals("15:00", endTimeGetBack);

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        eventMapper.deleteByPrimaryKey("event");
        eventMapper.deleteByPrimaryKey("event test");
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("appointmentsRecordsTest ends");
    }

    @AfterEach
    public void delete() {
        eventMapper.deleteByPrimaryKey("event");
        eventMapper.deleteByPrimaryKey("event test");
        appointmentMapper.deleteByPrimaryKey("1");
        System.out.println("deletion done");
    }

}