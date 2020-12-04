package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TicketServiceTest {
    @Autowired
    private BalanceService balanceService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketService ticketService;

    @Test
    public void addTicketTest() throws BusinessException, ParseException {
        System.out.println("addTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 0);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        String addTicketReturn = ticketService.addTicket(ticket, userModel.getUserId());
        System.out.println(addTicketReturn);

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 0, ticketGetBack.getPrice());

        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("addTicketTest ends");
    }

    @Test
    public void addNoEnoughMoneyTicketTest() throws BusinessException, ParseException {
        System.out.println("addNoEnoughMoneyTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        try {
            ticketService.addTicket(ticket, userModel.getUserId());
        } catch (BusinessException businessException) {
            assertEquals(301, businessException.getErrCode());
        }


        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("addNoEnoughMoneyTicketTest ends");
    }

    @Test
    public void addDuplicateTicketTest() throws BusinessException, ParseException {
        System.out.println("addDuplicateTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());
        balanceService.addBalance(userModel.getUserId(), (float)248);
        System.out.println(userModel.getUserId() + "has " + balanceService.queryBalance(userModel.getUserId()));
        ticketService.addTicket(ticket, userAccount.get(0).getUserId());


        try {
            ticketService.addTicket(ticket, userAccount.get(0).getUserId());
        } catch (BusinessException businessException) {
            assertEquals(242, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("addDuplicateTicketTest ends");
    }

    @Test
    public void addUserNotExistTicketTest() throws BusinessException, ParseException {
        System.out.println("addUserNotExistTicketTest starts");

        Ticket ticket = new Ticket();
        ticket.setUserId(2);
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        try {
            ticketService.addTicket(ticket, 2);
        } catch (BusinessException businessException) {
            System.out.println(businessException.getErrCode());
            assertEquals(20001, businessException.getErrCode());
        }

        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("addUserNotExistTicketTest ends");
    }

    @Test
    public void addPassedTicketTest() throws BusinessException, ParseException {
        System.out.println("addPassedTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        Calendar c = Calendar.getInstance();
        Date now = new Date();
        c.setTime(now);
        System.out.println("Today is " + now);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterdayBefore = c.getTime();
        System.out.println("Yesterday before yesterday is " + yesterdayBefore);
        ticket.setValidDate(yesterdayBefore);

        try {
            ticketService.addTicket(ticket, userModel.getUserId());
            System.out.println("successful");
        } catch (BusinessException businessException) {
            System.out.println(businessException.getErrCode());
            assertEquals(243, businessException.getErrCode());
        }

        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("addPassedTicketTest ends");
    }

    @Test
    public void updateTicketTest() throws BusinessException {
        System.out.println("updateTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        ErrorEnum updateTicketReturn = ticketService.updateTicket(ticket);
        System.out.println(updateTicketReturn);

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 100, ticketGetBack.getPrice());

        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("updateTicketTest ends");
    }

    @Test
    public void updateEmptyTicketTest() throws BusinessException {
        System.out.println("updateEmptyTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        ErrorEnum updateTicketReturn = ticketService.updateTicket(null);
        assertEquals(241, updateTicketReturn.getErrCode());


        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("updateEmptyTicketTest ends");
    }

    @Test
    public void updateUnmatchedTicketTest() throws BusinessException {
        System.out.println("updateUnmatchedTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        ticket.setTicketId("2");
        ErrorEnum updateTicketReturn = ticketService.updateTicket(ticket);
        assertEquals(244, updateTicketReturn.getErrCode());


        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("updateUnmatchedTicketTest ends");
    }

    @Test
    public void updateUserNotExistTicketTest() throws BusinessException {
        System.out.println("updateUserNotExistTicketTest starts");


        Ticket ticket = new Ticket();
        ticket.setUserId(1);
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        ErrorEnum updateTicketReturn = ticketService.updateTicket(ticket);
        assertEquals(20001, updateTicketReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(1);
        userPasswordMapper.deleteByPrimaryKey(1);
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("updateUserNotExistTicketTest ends");
    }

    @Test
    public void deleteTicketTest() throws BusinessException {
        System.out.println("deleteTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        ticketMapper.insertSelective(ticket);

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 124, ticketGetBack.getPrice());

        ErrorEnum deleteTicketReturn = ticketService.deleteTicket("1", userModel);
        System.out.println(deleteTicketReturn);

        ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertNull(ticketGetBack);

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());

        System.out.println("deleteTicketTest ends");
    }

    @Test
    public void deleteNullIdTicketTest() throws BusinessException {
        System.out.println("deleteNullIdTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        ErrorEnum deleteTicketReturn = ticketService.deleteTicket(null, userModel);
        assertEquals(241, deleteTicketReturn.getErrCode());


        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());

        System.out.println("deleteNullIdTicketTest ends");
    }

    @Test
    public void deleteUnmatchedTicketTest() throws BusinessException {
        System.out.println("deleteUnmatchedTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        ErrorEnum deleteTicketReturn = ticketService.deleteTicket("1", userModel);
        assertEquals(244, deleteTicketReturn.getErrCode());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());

        System.out.println("deleteUnmatchedTicketTest ends");
    }

    @Test
    public void deleteWrongUserTicketTest() throws BusinessException {
        System.out.println("deleteUnmatchedTicketTest starts");

        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria userAccountExampleCriteria = userAccountExample.createCriteria();
        userAccountExampleCriteria.andPhoneNumberEqualTo("212121");
        List<UserAccount> userAccount = userAccountMapper.selectByExample(userAccountExample);

        Ticket ticket = new Ticket();
        ticket.setUserId(userAccount.get(0).getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        ticketMapper.insertSelective(ticket);

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 124, ticketGetBack.getPrice());

        userModel.setUserId(1);
        ErrorEnum deleteTicketReturn = ticketService.deleteTicket("1", userModel);
        assertEquals(255, deleteTicketReturn.getErrCode());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.get(0).getUserId());

        System.out.println("deleteWrongUserTicketTest ends");
    }

    @Test
    public void ticketsRecordsTest() throws BusinessException {
        System.out.println("ticketsRecordsTest starts");

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
        List<Ticket> ticketList = ticketService.getTicketsByUserId(userModel.getUserId());
        assertEquals(Status.used, ticketList.get(0).getStatus());
        assertEquals((float) 124, ticketList.get(0).getPrice());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("ticketsRecordsTest ends");
    }

    @Test
    public void emptyTicketTest() throws ParseException, BusinessException {
        Ticket ticket = null;
        try {
            ticketService.addTicket(ticket, 1);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.EMPTY_TICKET, businessException.getCommonError());
        }
    }

}
