package com.java.rollercoaster.acceptancetest;

import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.response.CommonReturnType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherAcceptanceTest {
    @Autowired
    private UserAccountMapper userAccountMapper;

    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void queryWeatherAcceptanceTest() {
        //register
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        paramMap.add("email", "111111@qq.com");
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


        String url2 = "http://localhost:8080/weather/query";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie",cookie );
        MultiValueMap<String, Object> paramMap2 = new LinkedMultiValueMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(new Date());
        paramMap2.add("date", dateString);
        HttpEntity<MultiValueMap<String, Object>> httpEntity1 = new HttpEntity<>(paramMap2, headers1);
        ResponseEntity<CommonReturnType> response = restTemplate.postForEntity(url2, httpEntity1, CommonReturnType.class);

        assertEquals("success", response.getBody().getStatus());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("6789");
    }
    private String getCookie(ResponseEntity responseEntity) {
        String cookie = responseEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println(cookie);
        return cookie;
    }
}