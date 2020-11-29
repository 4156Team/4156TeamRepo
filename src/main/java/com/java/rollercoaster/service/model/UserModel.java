package com.java.rollercoaster.service.model;

import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;

import java.io.Serializable;

public class UserModel implements Serializable {


    private Integer userId;
    private String phoneNumber;
    private String userName;
    private UserGender userGender;
    private Integer userAge;
    private Role role;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    private String thirdPartyId;

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

    public com.java.rollercoaster.service.model.enumeration.UserGender getUserGender() {
        return userGender;
    }

    public void setUserGender(
            com.java.rollercoaster.service.model.enumeration.UserGender userGender) {
        this.userGender = userGender;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public com.java.rollercoaster.service.model.enumeration.Role getRole() {
        return role;
    }

    public void setRole(com.java.rollercoaster.service.model.enumeration.Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
