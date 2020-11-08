package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserPassword;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;

//    @Override
//    public UserModel getUserByPhoneNumber(String phoneNumber) {
//        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(phoneNumber);
//        if (userAccount == null) {
//            return null;
//        }
//        UserPassword userPassword = userPasswordMapper.selectByPrimaryKey(phoneNumber);
//        return convertFromDataObject(userAccount, userPassword);
//
//    }

    @Override
    public UserModel getUserByUserId(Integer userId) {
        UserAccount userAccount = userAccountMapper.selectByPrimaryKey(userId);
        if (userAccount == null) {
            return null;
        }
        UserPassword userPassword = userPasswordMapper.selectByPrimaryKey(userId);
        return convertFromDataObject(userAccount, userPassword);
    }

    private  UserModel convertFromDataObject(UserAccount userAccount, UserPassword userpassword) {
        if (userAccount == null) {
            return  null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userAccount, userModel);
        if (userpassword != null) {
            userModel.setPassword(userpassword.getPassword());
        }
        return  userModel;
    }
}
