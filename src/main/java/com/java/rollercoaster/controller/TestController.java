package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.EventMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private FacilityMapper facilityMapper;
    @Autowired
    private EventMapper eventMapper;
    //加上responseBody注解后 就是返回json 否则redirect到前端界面
    @RequestMapping("/test1")
    @ResponseBody
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
//        Facility facility = facilityMapper.selectByPrimaryKey("Walkhammer");
////        eventMapper.selectByPrimaryKey("11");
//        System.out.println("***********");
//        return facility.getFacilityStatus().toString();
//        return "success";
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey("6467124150");
        if(userAccount == null) {
            return "user not exist";
        }else {
            return userAccount.getUserName();
        }
//        return "sucess";
    }
}
