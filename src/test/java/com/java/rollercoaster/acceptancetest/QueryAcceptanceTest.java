package com.java.rollercoaster.acceptancetest;

import static org.junit.jupiter.api.Assertions.assertEquals;


import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryAcceptanceTest {

    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private FacilityMapper facilityMapper;

    //@BeforeEach
    public void init() throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Event event = new Event();
        event.setEventLocation("test location");
        event.setEventIntroduction("test introduction");
        event.setEventRemainPositions(1000);
        event.setStartTime(format.parse("10:30:00"));
        event.setEndTime(format.parse("11:00:00"));
        event.setEventName("testEvent");
        eventMapper.insert(event);

        Facility facility = new Facility();
        facility.setFacilityName("testFacility");
        facility.setFacilityIntroduction("test facility introduction");
        facility.setFacilityOpenTime(format.parse("11:30:00"));
        facility.setFacilityCloseTime(format.parse("12:00:00"));
        facility.setFacilityStatus(FacilityStatus.normal);
        facility.setQueueStatus(100);
        facilityMapper.insert(facility);

        System.out.println("start test");

    }


    /**
     * Acceptance test for querying event Location.
     */
    @Test
    public void test2() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Event";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("eventName","testEvent");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);

        //System.out.println(response.getBody().getData().getClass().toString());
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());

        assertEquals("test location", rs.get("eventLocation"));
        finish();

    }

    /**
     * Acceptance test for querying event description.
     */
    @Test
    public void test3() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Event";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("eventName","testEvent");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals("test introduction", rs.get("eventIntroduction"));
        finish();

    }

    /**
     * Acceptance test for querying event time.
     */
    @Test
    public void test4() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Event";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("eventName","testEvent");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);

        //System.out.println(response.getBody().getData().getClass().toString());
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals("10:30:00", rs.get("startTime"));
        assertEquals("11:00:00", rs.get("endTime"));
        finish();

    }

    /**
     * Acceptance test for querying event availability.
     */
    @Test
    public void test5() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Event";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("eventName","testEvent");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals(1000, rs.get("eventRemainPositions"));
        finish();

    }

    /**
     * Acceptance test for querying facility status.
     */
    @Test
    public void test7() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Facility";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("facilityName","testFacility");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals("normal", rs.get("facilityStatus"));
        finish();
    }

    /**
     * Acceptance test for querying facility introduction.
     */
    @Test
    public void test8() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Facility";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("facilityName","testFacility");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals("test facility introduction", rs.get("facilityIntroduction"));
        finish();
    }

    /**
     * Acceptance test for querying facility time.
     */
    @Test
    public void test9() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Facility";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("facilityName","testFacility");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        System.out.println(response.getBody().getData().getClass().toString());
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals("11:30:00", rs.get("facilityOpenTime"));
        assertEquals("12:00:00", rs.get("facilityCloseTime"));
        finish();
    }

    /**
     * Acceptance test for querying facility queue status.
     */
    @Test
    public void test10() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Facility";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("facilityName","testFacility");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        System.out.println(response.getBody().getData().getClass().toString());
        LinkedHashMap rs = ((LinkedHashMap)response.getBody().getData());
        assertEquals(100, rs.get("queueStatus"));
        finish();
    }

    @Test
    public void testWrongEventName() throws ParseException {
        finish();
        init();
        String url = "http://localhost:8080/query/Event";
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("eventName","kkkk");
        ResponseEntity<CommonReturnType> response =
                restTemplate.getForEntity(builder.toUriString(), CommonReturnType.class);
        CommonReturnType result = response.getBody();
        assertEquals("fail", result.getStatus());
        assertEquals(ErrorEnum.NO_SUCH_EVENT, ErrorEnum.valueOf((String) result.getData()));
        finish();
    }

    //@AfterEach
    public void finish() throws ParseException{
        eventMapper.deleteByPrimaryKey("testEvent");
        facilityMapper.deleteByPrimaryKey("testFacility");
    }
}
