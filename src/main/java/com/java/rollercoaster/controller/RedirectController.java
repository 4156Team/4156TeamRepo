package com.java.rollercoaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RedirectController {
    @RequestMapping("/redirect")
    public String redirect(HttpServletRequest servletRequest){
        return servletRequest.getParameter("url");
    }
}
