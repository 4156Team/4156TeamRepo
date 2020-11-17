package com.java.rollercoaster.acceptancetest;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.TicketExample;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
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

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAcceptanceTest {
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private TicketMapper ticketMapper;

//    @Test
//    public void registerTest() {
//        String url = "http://localhost:8080/user/register";
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
//        paramMap.add("telephone", "6789");
//        paramMap.add("name", "James");
//        paramMap.add("gender", "male");
//        paramMap.add("age", 18);
//        paramMap.add("password", "6789");
//        CommonReturnType response = restTemplate.postForObject(url, paramMap, CommonReturnType.class);
//        System.out.println(response.getStatus());
//        UserAccountExample example = new UserAccountExample();
//        UserAccountExample.Criteria criteria = example.createCriteria();
//        criteria.andPhoneNumberEqualTo("6789");
//        List<UserAccount> userAccount = userAccountMapper.selectByExample(example);
//        assertEquals("success", response.getStatus());
//        assertEquals("James", userAccount.get(0).getUserName());
//        userAccountMapper.deleteByExample(example);
//
//    }
//
//    @Test
//    public void loginTest() {
//        String url = "http://localhost:8080/user/register";
//        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
//        paramMap.add("telephone", "6789");
//        paramMap.add("name", "James");
//        paramMap.add("gender", "male");
//        paramMap.add("age", 18);
//        paramMap.add("password", "6789");
//        restTemplate.postForObject(url, paramMap, CommonReturnType.class);
//
//        MultiValueMap<String, Object> paramMap1 = new LinkedMultiValueMap<String, Object>();
//        String url1 = "http://localhost:8080/user/login";
//        paramMap1.add("telephone", "6789");
//        paramMap1.add("password", "6789");
//        CommonReturnType response = restTemplate.postForObject(url1, paramMap1, CommonReturnType.class);
//        assertEquals("success", response.getStatus());
//        assertEquals(Role.visitor, Role.valueOf((String) response.getData()));
//
//        UserAccountExample example = new UserAccountExample();
//        UserAccountExample.Criteria criteria = example.createCriteria();
//        criteria.andPhoneNumberEqualTo("6789");
//        userAccountMapper.deleteByExample(example);
//    }

    @Test
    public void buyTicketsTest() {
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

        //buy tickets
        Ticket ticket = new Ticket();
        ticket.setValidDate(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)));
        ticket.setPrice((float) 100);
        ticket.setStatus(Status.unused);
        String url2 = "http://localhost:8080/ticket/addTicket";
        HttpHeaders headers1 = new HttpHeaders();
        headers1.add("Cookie",cookie );
        HttpEntity<Ticket> httpEntity1 = new HttpEntity<>(ticket, headers1);
        ResponseEntity<CommonReturnType> response = restTemplate.postForEntity(url2, httpEntity1, CommonReturnType.class);

        assertEquals("success", response.getBody().getStatus());

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("6789");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria ticketCriteria = ticketExample.createCriteria();
        ticketCriteria.andUserIdEqualTo(userAccount.get(0).getUserId());
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        assertEquals(100, ticketList.get(0).getPrice());


        //delete record
        userAccountMapper.deleteByExample(userAccountExample);
        ticketMapper.deleteByExample(ticketExample);



    }

    private String getCookie(ResponseEntity responseEntity) {
        String cookie = responseEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println(cookie);
        return cookie;
    }


}
