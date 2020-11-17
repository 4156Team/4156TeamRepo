package com.java.rollercoaster.acceptancetest;

import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAcceptanceTest {
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Test
    public void registerTest() {
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        CommonReturnType response = restTemplate.postForObject(url, paramMap, CommonReturnType.class);
        System.out.println(response.getStatus());
        UserAccountExample example = new UserAccountExample();
        UserAccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNumberEqualTo("6789");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(example);
        assertEquals("success", response.getStatus());
        assertEquals("James", userAccount.get(0).getUserName());
        userAccountMapper.deleteByExample(example);

    }

    @Test
    public void loginTest() {
        String url = "http://localhost:8080/user/register";
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("telephone", "6789");
        paramMap.add("name", "James");
        paramMap.add("gender", "male");
        paramMap.add("age", 18);
        paramMap.add("password", "6789");
        restTemplate.postForObject(url, paramMap, CommonReturnType.class);

        MultiValueMap<String, Object> paramMap1 = new LinkedMultiValueMap<String, Object>();
        String url1 = "http://localhost:8080/user/login";
        paramMap1.add("telephone", "6789");
        paramMap1.add("password", "6789");
        CommonReturnType response = restTemplate.postForObject(url1, paramMap1, CommonReturnType.class);
        assertEquals("success", response.getStatus());
        assertEquals(Role.visitor, Role.valueOf((String) response.getData()));

        UserAccountExample example = new UserAccountExample();
        UserAccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNumberEqualTo("6789");
        userAccountMapper.deleteByExample(example);
    }


}
