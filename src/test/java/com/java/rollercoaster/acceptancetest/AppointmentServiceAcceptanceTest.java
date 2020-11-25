package com.java.rollercoaster.acceptancetest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.rollercoaster.dao.*;
import com.java.rollercoaster.pojo.*;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentServiceAcceptanceTest {
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;

    @Test
    public void makeAppointment(){
        //register
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        restTemplate.postForObject(url, paramMap, CommonReturnType.class);

        //log in
        MultiValueMap<String, Object> paramMap1 = new LinkedMultiValueMap<String, Object>();
        String url1 = "http://localhost:8080/user/login";
        paramMap1.add("telephone", "6789");
        paramMap1.add("password", "6789");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity =
                new HttpEntity<MultiValueMap<String, Object>>(paramMap1, headers);
        //restTemplate.postForObject(url1, paramMap1, CommonReturnType.class);
        ResponseEntity<CommonReturnType> responseEntity = restTemplate.postForEntity(url1, httpEntity, CommonReturnType.class);
        String cookie = getCookie(responseEntity);

        //insert event test to the table
        Event event = initEvent(10, "event test");
        //add appointments
        Appointment appointment = new Appointment();
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        String url2 = "http://localhost:8080/appointment/addAppointment";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie",cookie );
        HttpEntity<Appointment> httpEntity1 = new HttpEntity<>(appointment, headers1);
        ResponseEntity<CommonReturnType> response = restTemplate.postForEntity(url2, httpEntity1, CommonReturnType.class);

        assertEquals("success", response.getBody().getStatus());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("6789");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        int userId = userAccount.get(0).getUserId();
        AppointmentExample appointmentExample = new AppointmentExample();
        AppointmentExample.Criteria appointmentCriteria = appointmentExample.createCriteria();
        appointmentCriteria.andUserIdEqualTo(userAccount.get(0).getUserId());
        List<Appointment> appointmentsList = appointmentMapper.selectByExample(appointmentExample);
        assertEquals("event test", appointmentsList.get(0).getEventName());
        assertEquals(9, eventMapper.selectByPrimaryKey("event test").getEventRemainPositions());

        //delete record
        userAccountMapper.deleteByExample(userAccountExample);
        userPasswordMapper.deleteByPrimaryKey(userId);
        eventMapper.deleteByPrimaryKey("event test");
        appointmentMapper.deleteByExample(appointmentExample);


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

    private String getCookie(ResponseEntity responseEntity) {
        String cookie = responseEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println(cookie);
        return cookie;
    }

    @Test
    public void displayAppointmentsTest() throws JsonProcessingException {
        //register
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        restTemplate.postForObject(url, paramMap, CommonReturnType.class);

        //log in
        MultiValueMap<String, Object> paramMap1 = new LinkedMultiValueMap<String, Object>();
        String url1 = "http://localhost:8080/user/login";
        paramMap1.add("telephone", "6789");
        paramMap1.add("password", "6789");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity =
                new HttpEntity<MultiValueMap<String, Object>>(paramMap1, headers);
        //restTemplate.postForObject(url1, paramMap1, CommonReturnType.class);
        ResponseEntity<CommonReturnType> responseEntity = restTemplate.postForEntity(url1, httpEntity, CommonReturnType.class);
        String cookie = getCookie(responseEntity);

        //add appointments
        Event event = initEvent(10, "event test");
        Appointment appointment = new Appointment();
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        String url2 = "http://localhost:8080/appointment/addAppointment";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie",cookie );
        HttpEntity<Appointment> httpEntity1 = new HttpEntity<>(appointment, headers1);
        ResponseEntity<CommonReturnType> response = restTemplate.postForEntity(url2, httpEntity1, CommonReturnType.class);

        assertEquals("success", response.getBody().getStatus());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("6789");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        int userId = userAccount.get(0).getUserId();
        AppointmentExample appointmentExample = new AppointmentExample();
        AppointmentExample.Criteria appointmentCriteria = appointmentExample.createCriteria();
        appointmentCriteria.andUserIdEqualTo(userAccount.get(0).getUserId());
        List<Appointment> appointmentsList = appointmentMapper.selectByExample(appointmentExample);

        //display appointments
        String url3 = "http://localhost:8080/appointment/appointmentsRecord";
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Cookie",cookie );
        HttpEntity httpEntity2 = new HttpEntity(headers2);
        response = restTemplate.exchange(url3, HttpMethod.GET,httpEntity2,CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());
        System.out.println(response.getBody());
        System.out.println(response.getBody().getData().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        String res = objectMapper.writeValueAsString(response.getBody().getData());
        System.out.println(res);
        List<Appointment> appointments = objectMapper.readValue(res, new TypeReference<List<Appointment>>() {
        });

        assertEquals("event test", appointments.get(0).getEventName());
        assertEquals(userId, appointments.get(0).getUserId());

        //delete record
        userAccountMapper.deleteByExample(userAccountExample);
        userPasswordMapper.deleteByPrimaryKey(userId);
        eventMapper.deleteByPrimaryKey("event test");
        appointmentMapper.deleteByExample(appointmentExample);
    }

    @Test
    public void cancelAppointmentsTest() {
        //register
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        restTemplate.postForObject(url, paramMap, CommonReturnType.class);

        //log in
        MultiValueMap<String, Object> paramMap1 = new LinkedMultiValueMap<String, Object>();
        String url1 = "http://localhost:8080/user/login";
        paramMap1.add("telephone", "6789");
        paramMap1.add("password", "6789");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity =
                new HttpEntity<MultiValueMap<String, Object>>(paramMap1, headers);
        //restTemplate.postForObject(url1, paramMap1, CommonReturnType.class);
        ResponseEntity<CommonReturnType> responseEntity = restTemplate.postForEntity(url1, httpEntity, CommonReturnType.class);
        String cookie = getCookie(responseEntity);


        //insert event test to the table
        Event event = initEvent(10, "event test");
        //add appointments
        Appointment appointment = new Appointment();
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        String url2 = "http://localhost:8080/appointment/addAppointment";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie",cookie );
        HttpEntity<Appointment> httpEntity1 = new HttpEntity<>(appointment, headers1);
        ResponseEntity<CommonReturnType> response = restTemplate.postForEntity(url2, httpEntity1, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("6789");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        int userId = userAccount.get(0).getUserId();
        AppointmentExample appointmentExample = new AppointmentExample();
        AppointmentExample.Criteria appointmentCriteria = appointmentExample.createCriteria();
        appointmentCriteria.andUserIdEqualTo(userAccount.get(0).getUserId());
        List<Appointment> appointmentsList = appointmentMapper.selectByExample(appointmentExample);

        //cancel appointments
        String url3 = "http://localhost:8080/appointment/deleteAppointment";
        Appointment appointment1 = appointmentsList.get(0);
        MultiValueMap<String, Object> paramMap2 = new LinkedMultiValueMap<String, Object>();
        paramMap2.add("appointmentId", appointment1.getAppointmentId());
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Cookie",cookie );
        HttpEntity httpEntity2 = new HttpEntity(paramMap2,headers2);
        response = restTemplate.postForEntity(url3,httpEntity2,CommonReturnType.class);
        assertEquals(null, appointmentMapper.selectByPrimaryKey(appointment1.getAppointmentId()));

        //delete record
        userAccountMapper.deleteByExample(userAccountExample);
        userPasswordMapper.deleteByPrimaryKey(userId);
        eventMapper.deleteByPrimaryKey("event test");
    }

    @Test
    public void updateAppointmentsTest() {
        //register
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        restTemplate.postForObject(url, paramMap, CommonReturnType.class);

        //log in
        MultiValueMap<String, Object> paramMap1 = new LinkedMultiValueMap<String, Object>();
        String url1 = "http://localhost:8080/user/login";
        paramMap1.add("telephone", "6789");
        paramMap1.add("password", "6789");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity =
                new HttpEntity<MultiValueMap<String, Object>>(paramMap1, headers);
        //restTemplate.postForObject(url1, paramMap1, CommonReturnType.class);
        ResponseEntity<CommonReturnType> responseEntity = restTemplate.postForEntity(url1, httpEntity, CommonReturnType.class);
        String cookie = getCookie(responseEntity);


        //insert event test to the table
        Event event = initEvent(10, "event test");
        Event anotherEvent = initEvent(5, "event another");
        //add appointments
        Appointment appointment = new Appointment();
        appointment.setEventName("event test");
        appointment.setValidDate(new Date());
        String url2 = "http://localhost:8080/appointment/addAppointment";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie",cookie );
        HttpEntity<Appointment> httpEntity1 = new HttpEntity<>(appointment, headers1);
        ResponseEntity<CommonReturnType> response = restTemplate.postForEntity(url2, httpEntity1, CommonReturnType.class);
        assertEquals("success", response.getBody().getStatus());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("6789");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);
        int userId = userAccount.get(0).getUserId();

        AppointmentExample appointmentExample = new AppointmentExample();
        AppointmentExample.Criteria appointmentCriteria = appointmentExample.createCriteria();
        appointmentCriteria.andUserIdEqualTo(userAccount.get(0).getUserId());
        List<Appointment> appointmentsList = appointmentMapper.selectByExample(appointmentExample);

        Appointment appointment1 = appointmentsList.get(0);
        appointment1.setEventName("event another");
        //update appointments
        String url3 = "http://localhost:8080/appointment/updateAppointment";

        MultiValueMap<String, Object> paramMap2 = new LinkedMultiValueMap<String, Object>();
        paramMap2.add("appointmentId", appointment1.getAppointmentId());
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Cookie",cookie );
        HttpEntity<Appointment> httpEntity2 = new HttpEntity<>(appointment1, headers2);
        response = restTemplate.postForEntity(url3,httpEntity2,CommonReturnType.class);
        assertEquals("event another",
                appointmentMapper.selectByPrimaryKey(appointment1.getAppointmentId()).getEventName());
        assertEquals(userId, appointmentMapper.selectByPrimaryKey(appointment1.getAppointmentId()).getUserId());

        //delete record
        userAccountMapper.deleteByExample(userAccountExample);
        userPasswordMapper.deleteByPrimaryKey(userId);
        eventMapper.deleteByPrimaryKey("event test");
        eventMapper.deleteByPrimaryKey("event another");
        appointmentMapper.deleteByPrimaryKey(appointment1.getAppointmentId());
    }

}