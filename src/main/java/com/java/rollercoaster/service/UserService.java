package com.java.rollercoaster.service;

import com.java.rollercoaster.service.model.UserModel;

public interface UserService {
//    UserModel getUserByPhoneNumber(String phoneNumber);
    UserModel getUserByUserId(Integer userId);
}
