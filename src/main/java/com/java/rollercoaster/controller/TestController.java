package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private TicketMapper ticketMapper;
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
        System.out.println("***********");
        return facility.getFacilityStatus().toString();
//        return "success";
    }

    @RequestMapping("/test4")
    @ResponseBody
    public String test4(@RequestParam String ticketId){
        Ticket ticket = ticketMapper.selectByPrimaryKey(ticketId);
        if (null == ticket){
            return "No such ticket";
        }
        return ticket.getValidDate().toString();
    }
}
