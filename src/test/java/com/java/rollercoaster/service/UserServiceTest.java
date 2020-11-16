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
    public void registerTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
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
    public void ticketsRecordsTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        Ticket ticket = new Ticket();
        ticket.setUserId(userModel.getUserId());
        ticket.setStatus(Status.used);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());
        ticketMapper.insertSelective(ticket);
        List<Ticket> ticketList = userService.getTicketsByUserId(userModel.getUserId());
        assertEquals(Status.used, ticketList.get(0).getStatus());
        assertEquals((float) 124, ticketList.get(0).getPrice());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
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
