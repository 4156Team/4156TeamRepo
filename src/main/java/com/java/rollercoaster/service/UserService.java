package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.service.model.UserModel;

import java.util.List;

public interface UserService {

    void register(UserModel userModel) throws BusinessException;

    UserModel loginWithGoogle(UserAccount userAccount) throws BusinessException;

    UserModel validateLogin(String telphone, String encryptPassword) throws BusinessException;


}
