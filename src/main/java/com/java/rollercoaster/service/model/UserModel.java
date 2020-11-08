package com.java.rollercoaster.service.model;

import com.java.rollercoaster.pojo.enumeration.Role;
import com.java.rollercoaster.pojo.enumeration.UserGender;

public class UserModel {


    private Integer userId;
    private String phoneNumber;
    private String userName;
    private UserGender UserGender;
    private Integer userAge;
    private Role Role;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public com.java.rollercoaster.pojo.enumeration.Role getRole() {
        return Role;
    }

    public void setRole(com.java.rollercoaster.pojo.enumeration.Role role) {
        Role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
