package com.java.rollercoaster;

import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.pojo.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@MapperScan("com.java.rollercoaster.dao")
public class RollercoasterApplication {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @RequestMapping("/")
    public String home() {
//        UserAccount userAccount = userAccountMapper.selectByPrimaryKey("123456");
//        if(userAccount == null) {
//            return "user not exist";
//        }else {
//            return userAccount.getUserName();
//        }
        return "hello world";

    }

    public static void main(String[] args) {
        SpringApplication.run(RollercoasterApplication.class, args);
    }


}
