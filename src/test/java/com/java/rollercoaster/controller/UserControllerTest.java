package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.pojo.UserPassword;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    private int id;
    private MessageDigest md5;
    private BASE64Encoder base64Encoder = new BASE64Encoder();

    public void init() throws NoSuchAlgorithmException {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserGender(UserGender.male);
        userAccount.setUserName("test");
        userAccount.setUserAge(20);
        userAccount.setRole(Role.visitor);
        userAccount.setPhoneNumber("118118118");
        userAccountMapper.insert(userAccount);
        id = userAccount.getUserId();
        UserPassword userPassword = new UserPassword();
        md5 = MessageDigest.getInstance("MD5");
        userPassword.setPassword(base64Encoder.encode(md5.digest("1234".getBytes(StandardCharsets.UTF_8))));
        userPassword.setUserId(id);
        userPasswordMapper.insert(userPassword);

    }

    @Test
    public void testLogIn() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        init();
        CommonReturnType commonReturnType = userController.login("118118118", "1234");
        assertEquals("success", commonReturnType.getStatus());
        assertEquals(Role.visitor, commonReturnType.getData());
        finish();
    }

    @Test
    public void testRegister() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        init();
        CommonReturnType commonReturnType = userController.register(
                "120120120", "m", "male", 29, "1234"
        );
        assertEquals("success", commonReturnType.getStatus());
        UserAccountExample example = new UserAccountExample();
        UserAccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNumberEqualTo("120120120");
        UserAccount userAccount = userAccountMapper.selectByExample(example).get(0);
        assertEquals("120120120", userAccount.getPhoneNumber());
        assertEquals(29, userAccount.getUserAge());
        assertEquals("m", userAccount.getUserName());
        assertEquals(UserGender.male, userAccount.getUserGender());
        assertEquals(base64Encoder.encode(md5.digest("1234".getBytes(StandardCharsets.UTF_8))), userPasswordMapper.selectByPrimaryKey(userAccount.getUserId()).getPassword());
        userAccountMapper.deleteByPrimaryKey(userAccount.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.getUserId());
        finish();
    }

    @Test
    public void testEncodeMD5() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        init();
        assertEquals(base64Encoder.encode(md5.digest("1234".getBytes(StandardCharsets.UTF_8))),
                userController.encodeByMd5("1234"));
        finish();
    }

    @Test
    public void verifyToken() {

    }

    public void finish() {
        userPasswordMapper.deleteByPrimaryKey(id);
        userAccountMapper.deleteByPrimaryKey(id);
    }
}
