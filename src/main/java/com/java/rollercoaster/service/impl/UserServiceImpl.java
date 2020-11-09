package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.pojo.UserPassword;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static java.util.UUID.randomUUID;

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



    @Override
    @Transactional
    public void register(UserModel userModel) throws BusinessException {
        if(userModel == null) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        if(StringUtils.isEmpty(userModel.getUserName()) || userModel.getUserGender() == null
        || userModel.getUserAge() == null || StringUtils.isEmpty(userModel.getPhoneNumber())) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        UserAccount userAccount = convertFromModel(userModel);
        try{
            userAccountMapper.insertSelective(userAccount);
        }catch (DuplicateKeyException ex) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR,"This telephone has been registered");
        }
        userModel.setUserId(userAccount.getUserId());

        UserPassword userPassword = convertPasswordFromModel(userModel);
        userPasswordMapper.insertSelective(userPassword);
        return;

    }

    @Override
    public UserModel validateLogin(String telphone, String encryptPassword) throws BusinessException {
        //get user information by user phone number
        UserAccountExample example = new UserAccountExample();
        UserAccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNumberEqualTo(telphone);
        UserAccount userAccount = Optional
                                    .ofNullable(userAccountMapper.selectByExample(example))
                                    .orElse(new ArrayList<>()).get(0);
        if (userAccount== null) {
            throw new BusinessException(ErrorEnum.USER_LOGIN_FAIL);
        }
        UserPassword userPassword = userPasswordMapper.selectByPrimaryKey(userAccount.getUserId());
        UserModel userModel = convertFromDataObject(userAccount, userPassword);
        //compare encrypt password with the input password
        if (!StringUtils.equals(encryptPassword, userModel.getPassword())) {
            throw new BusinessException(ErrorEnum.USER_LOGIN_FAIL);
        }
        return userModel;


    }

    private UserPassword convertPasswordFromModel(UserModel userModel) {
        if (userModel == null) {
            return  null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(userModel.getPassword());
        if (userModel.getUserId() == null) {
            System.out.println("no user id");
        }
        userPassword.setUserId(userModel.getUserId());
        return  userPassword;
    }

    private UserAccount convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return  null;
        }
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userModel,userAccount);
        return  userAccount;
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
