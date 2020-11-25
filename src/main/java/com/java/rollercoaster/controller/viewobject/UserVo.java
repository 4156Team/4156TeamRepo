package com.java.rollercoaster.controller.viewobject;

import com.java.rollercoaster.service.model.enumeration.UserGender;

public class UserVo {
    private String phoneNumber;
    private String userName;
    private UserGender userGender;
    private Integer userAge;
    private Integer userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

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

    public UserGender getUserGender() {
        return userGender;
    }

    public void setUserGender(UserGender userGender) {
        this.userGender = userGender;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
