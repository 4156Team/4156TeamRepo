package com.java.rollercoaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.java.rollercoaster.dao")
public class RollercoasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollercoasterApplication.class, args);
    }

}
