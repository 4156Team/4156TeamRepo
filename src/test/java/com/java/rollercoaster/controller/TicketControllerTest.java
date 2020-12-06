package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.BalanceMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Balance;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TicketControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private TicketController ticketController;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private BalanceMapper balanceMapper;

    private UserModel initUser(Role role) throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Tim");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(role);
        userModel.setPhoneNumber("137139");
        userModel.setPassword("12345");
        userModel.setEmail("yy2979@columbia.edu");
        userService.register(userModel);
        Balance balance = new Balance();
        balance.setBalance(2000f);
        balance.setQuickpass(10);
        balance.setUserId(userModel.getUserId());
        balanceMapper.insert(balance);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return userModel;
    }

    private Ticket initTicket(UserModel userModel) {
        Ticket ticket = new Ticket();
        ticket.setUserId(userModel.getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setValidDate(new Date());
        return ticket;
    }

    @Test
    public void addTicketControllerTest() throws BusinessException, ParseException, UnirestException {
        UserModel userModel = initUser(Role.visitor);
        Ticket ticket = initTicket(userModel);

        CommonReturnType response = ticketController.addTicket(ticket);
        assertEquals("success", response.getStatus());
        String ticketId = (String) response.getData();

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey(ticketId);
        assertEquals(userModel.getUserId(), ticketGetBack.getUserId());
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 124, ticketGetBack.getPrice());


        ticketMapper.deleteByPrimaryKey(ticketId);
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void addTicketNullLoginControllerTest() throws BusinessException, ParseException, UnirestException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        Ticket ticket = initTicket(userModel);

        CommonReturnType response = ticketController.addTicket(ticket);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void addTicketFalseLoginControllerTest() throws BusinessException, ParseException, UnirestException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        Ticket ticket = initTicket(userModel);

        CommonReturnType response = ticketController.addTicket(ticket);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void addTicketNullUserControllerTest() throws BusinessException, ParseException, UnirestException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        Ticket ticket = initTicket(userModel);

        CommonReturnType response = ticketController.addTicket(ticket);
        assertEquals(ErrorEnum.USER_NOT_EXIST, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void addErrorTicketControllerTest() throws BusinessException, ParseException, UnirestException {
        UserModel userModel = initUser(Role.visitor);

        Ticket ticket = initTicket(userModel);
        long millis = System.currentTimeMillis();
        long before = millis - 86400000L;
        ticket.setValidDate(new Date(before));
        CommonReturnType response = ticketController.addTicket(ticket);
        assertEquals(ErrorEnum.DATE_PASSED, response.getData());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void updateTicket() throws BusinessException {
        UserModel userModel = initUser(Role.manager);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        CommonReturnType response = ticketController.updateTicket(ticket);
        assertEquals("success", response.getStatus());

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertEquals(userModel.getUserId(), ticketGetBack.getUserId());
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 100, ticketGetBack.getPrice());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void updateVisitorTicket() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        CommonReturnType response = ticketController.updateTicket(ticket);
        assertEquals(ErrorEnum.NO_AUTHORIZATION, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }


    @Test
    public void updateFalseLoginTicket() throws BusinessException {
        UserModel userModel = initUser(Role.manager);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        CommonReturnType response = ticketController.updateTicket(ticket);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void updateNullLoginTicket() throws BusinessException {
        UserModel userModel = initUser(Role.manager);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        CommonReturnType response = ticketController.updateTicket(ticket);
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void updateNullUserTicket() throws BusinessException {
        UserModel userModel = initUser(Role.manager);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        ticket.setPrice((float) 100);
        CommonReturnType response = ticketController.updateTicket(ticket);
        assertEquals(ErrorEnum.USER_NOT_EXIST, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void deleteTicket() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.deleteTicket("1");
        assertEquals("success", response.getStatus());

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertNull(ticketGetBack);

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void deleteNullLoginTicket() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.deleteTicket("1");
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }


    @Test
    public void deleteFalseLoginTicket() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.deleteTicket("1");
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void deleteNullUserTicket() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.deleteTicket("1");
        assertEquals(ErrorEnum.USER_NOT_EXIST, response.getData());

        ticketMapper.deleteByPrimaryKey("1");
        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void getTickets() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.getTickets();
        assertEquals("success", response.getStatus());

        Ticket ticketGetBack = ((List<Ticket>)response.getData()).get(0);
        assertEquals((float) 124, ticketGetBack.getPrice());
        assertEquals("1", ticketGetBack.getTicketId());
        assertEquals(userModel.getUserId(), ticketGetBack.getUserId());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void getNullLoginTickets() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", null);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.getTickets();
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());


        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void getFalseLoginTickets() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("IS_LOGIN", false);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.getTickets();
        assertEquals(ErrorEnum.USER_NOT_LOGIN, response.getData());


        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }

    @Test
    public void getNullUserTickets() throws BusinessException {
        UserModel userModel = initUser(Role.visitor);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", null);
        Ticket ticket = initTicket(userModel);
        ticket.setTicketId("1");
        ticketMapper.insertSelective(ticket);

        CommonReturnType response = ticketController.getTickets();
        assertEquals(ErrorEnum.USER_NOT_EXIST, response.getData());


        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        balanceMapper.deleteByPrimaryKey(userModel.getUserId());
    }
}