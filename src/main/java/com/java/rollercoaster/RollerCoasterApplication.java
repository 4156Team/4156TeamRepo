package com.java.rollercoaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SuppressWarnings("checkstyle:hideutilityclassconstructor")
@SpringBootApplication
@MapperScan("com.java.rollercoaster.dao")
public class RollerCoasterApplication {
    /**
     * Spring boot initialization.
     *
     * @param args args.
     */
    public static void main(final String[] args) {

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(RollerCoasterApplication.class, args);
    }
}
