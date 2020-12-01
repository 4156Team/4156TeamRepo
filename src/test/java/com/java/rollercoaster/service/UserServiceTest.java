package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserPassword;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private TicketMapper ticketMapper;


    @Test
    public void registerWithValidInputTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userModel.setEmail("yl4225@columbia.edu");
        userService.register(userModel);

        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(userModel.getUserId());
        assertEquals("Alice", userAccount.getUserName());
        assertEquals(UserGender.female, userAccount.getUserGender());
        assertEquals(Role.visitor, userAccount.getRole());
        assertEquals("212121", userAccount.getPhoneNumber());

        UserPassword userPassword = userPasswordMapper.selectByPrimaryKey(userModel.getUserId());
        assertEquals("12345", userPassword.getPassword());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void nullUserModelRegister() throws BusinessException {
        UserModel userModel = null;
        assertEquals(ErrorEnum.PARAMETER_VALIDATION_ERROR, userService.register(userModel));
    }

    @Test
    public void duplicateRegister() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);
        try {
            userService.register(userModel);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.PARAMETER_VALIDATION_ERROR, businessException.getCommonError());
        }
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());

    }

    @Test
    public void nullGoogleLoginTest() {
        UserAccount userAccount = null;
        try {
            userService.loginWithGoogle(userAccount);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.USER_NOT_EXIST, businessException.getCommonError());
        }
    }




    @Test
    public void validateLoginTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserModel userModel1 = userService.validateLogin("212121", "12345");
        assertEquals("Alice", userModel1.getUserName());
        assertEquals(UserGender.female, userModel1.getUserGender());
        assertEquals(Role.visitor, userModel1.getRole());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void invalidValidateLoginTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        try {
            UserModel userModel1 = userService.validateLogin("12", "12345");
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.USER_LOGIN_FAIL, businessException.getCommonError());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void wrongPasswordLoginTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        try {
            UserModel userModel1 = userService.validateLogin("212121", "125");
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.USER_LOGIN_FAIL, businessException.getCommonError());
        }

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
    }



    @Test
    public void verifyTokenWithoutExistedAccountTest() throws BusinessException {
        UserAccount userAccount = new UserAccount();
        userAccount.setThirdPartyId("asdfg");
        userAccount.setUserName("Mike");
        UserModel userModel = userService.loginWithGoogle(userAccount);
        assertEquals("Mike", userModel.getUserName());
        assertEquals("asdfg", userModel.getThirdPartyId());
        assertEquals(userAccount.getUserId(), userModel.getUserId());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
    }
    @Test
    public void verifyTokenWithExistedAccountTest() throws BusinessException {
        UserAccount userAccount = new UserAccount();
        userAccount.setThirdPartyId("asdfg");
        userAccount.setUserName("Mike");
        userAccountMapper.insertSelective(userAccount);
        UserModel userModel = userService.loginWithGoogle(userAccount);
        assertEquals("Mike", userModel.getUserName());
        assertEquals("asdfg", userModel.getThirdPartyId());
        assertEquals(userAccount.getUserId(), userModel.getUserId());
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
    }

}
