package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.enumeration.Role;
import com.java.rollercoaster.pojo.enumeration.UserGender;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class TestController {
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserAccountMapper userAccountMapper;
    //加上responseBody注解后 就是返回json 否则redirect到前端界面
    @RequestMapping("/test1")
    public String index() {
        System.out.println("test1");
        return "/index";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String result(){
        System.out.println("test2");
        return "test2";
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String test(){
        Facility facility = facilityMapper.selectByPrimaryKey("Walkhammer");
//        eventMapper.selectByPrimaryKey("11");
//        System.out.println("***********");
//        return facility.getFacilityStatus().toString();
//        return "success";
        UserAccount userAccount = new UserAccount();
        userAccount.setPhoneNumber("18718691881");
        userAccount.setRole(Role.visitor);
        userAccount.setUserAge(22);
        userAccount.setUserName("Yuzhang");
        userAccount.setUserGender(UserGender.male);
        userAccountMapper.insertSelective(userAccount);
        System.out.println(userAccount.getUserId());
        return "*****";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public String test4(@RequestParam String ticketId){
        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        if (null == ticket){
            return "No such ticket";
        }
        System.out.println(new Date());
        Date date = new Date();
        System.out.println(DateUtils.isSameDay(date,ticket.getValidDate()));
        return ticket.getValidDate().toString();
    }

    @RequestMapping("/test5")
    public String test5(){
        return "/login";

    }

}
