package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TicketServiceTest {
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

        Ticket ticket = new Ticket();
        ticket.setUserId(userModel.getUserId());
        ticket.setStatus(Status.unused);
        ticket.setPrice((float) 124);
        ticket.setTicketId("1");
        ticket.setValidDate(new Date());

        String addTicketReturn = ticketService.addTicket(ticket, userModel.getUserId());
        System.out.println(addTicketReturn);

        Ticket ticketGetBack = ticketMapper.selectByPrimaryKey("1");
        assertEquals(Status.unused, ticketGetBack.getStatus());
        assertEquals((float) 124, ticketGetBack.getPrice());

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("addTicketTest ends");
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

        Ticket ticket = new Ticket();
        ticket.setUserId(userModel.getUserId());
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

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());
        ticketMapper.deleteByPrimaryKey("1");
        System.out.println("updateTicketTest ends");
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

        Ticket ticket = new Ticket();
        ticket.setUserId(userModel.getUserId());
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

        userAccountMapper.deleteByPrimaryKey(userModel.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userModel.getUserId());

        System.out.println("deleteTicketTest ends");
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
