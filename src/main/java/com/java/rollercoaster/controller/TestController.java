package com.java.rollercoaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
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
}
