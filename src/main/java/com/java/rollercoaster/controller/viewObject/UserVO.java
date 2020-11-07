package com.java.rollercoaster.controller.viewObject;

import com.java.rollercoaster.pojo.enumeration.UserGender;

public class UserVO {
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public com.java.rollercoaster.pojo.enumeration.UserGender getUserGender() {
        return UserGender;
    }

    public void setUserGender(com.java.rollercoaster.pojo.enumeration.UserGender userGender) {
        UserGender = userGender;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    private String phoneNumber;
    private String userName;
    private UserGender UserGender;
    private Integer userAge;
}
