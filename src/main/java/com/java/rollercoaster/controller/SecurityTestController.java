package com.java.rollercoaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {
    @GetMapping("/hello")
    public String hello() {
        return "testtest";
    }

}
