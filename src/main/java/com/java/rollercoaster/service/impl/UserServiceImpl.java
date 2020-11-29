package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.TicketExample;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private TicketMapper ticketMapper;


    @Override
    @Transactional
    public ErrorEnum register(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            return ErrorEnum.PARAMETER_VALIDATION_ERROR;
        }
        UserAccount userAccount = convertFromModel(userModel);
        try {
            userAccountMapper.insertSelective(userAccount);
        } catch (DuplicateKeyException ex) {
            return ErrorEnum.PARAMETER_VALIDATION_ERROR;
        }
        userModel.setUserId(userAccount.getUserId());

        UserPassword userPassword = convertPasswordFromModel(userModel);
        System.out.println(userPassword.getPassword());
        userPasswordMapper.insertSelective(userPassword);
        return ErrorEnum.OK;

    }

    @Override
    public UserModel loginWithGoogle(UserAccount userAccount) throws BusinessException {
        if (userAccount == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        UserAccountExample userAccountExample = new UserAccountExample();
        UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
        criteria.andThirdPartyIdEqualTo(userAccount.getThirdPartyId());
        List<UserAccount> candidates = userAccountMapper.selectByExample(userAccountExample);
        UserAccount existAccount = candidates.isEmpty() ?  null : candidates.get(0);

        UserModel userModel = new UserModel();
        if (existAccount == null) {
            userAccountMapper.insertSelective(userAccount);
            userModel.setUserId(userAccount.getUserId());
            userModel.setUserName(userAccount.getUserName());
            userModel.setThirdPartyId(userAccount.getThirdPartyId());
        } else {
            userModel.setUserId(existAccount.getUserId());
            userModel.setUserName(existAccount.getUserName());
            userModel.setThirdPartyId(existAccount.getThirdPartyId());
        }
        return userModel;
    }

    @Override
    public UserModel validateLogin(String telphone,
                                   String encryptPassword) throws BusinessException {
        //get user information by user phone number
        UserAccountExample example = new UserAccountExample();
        UserAccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNumberEqualTo(telphone);
        List<UserAccount> results = userAccountMapper.selectByExample(example);
        UserAccount userAccount = results.isEmpty() ? null : results.get(0);
        if (userAccount ==  null) {
            throw new BusinessException(ErrorEnum.USER_LOGIN_FAIL);
        }
        UserPassword userPassword = userPasswordMapper.selectByPrimaryKey(userAccount.getUserId());
        UserModel userModel = convertFromDataObject(userAccount, userPassword);
        //compare encrypt password with the input password
        System.out.println(userPassword.getPassword());
        System.out.println(encryptPassword);
        if (!StringUtils.equals(encryptPassword, userModel.getPassword())) {
            throw new BusinessException(ErrorEnum.USER_LOGIN_FAIL);
        }
        return userModel;

    }

    private UserPassword convertPasswordFromModel(UserModel userModel) {
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(userModel.getPassword());
        userPassword.setUserId(userModel.getUserId());
        return  userPassword;
    }

    private UserAccount convertFromModel(UserModel userModel) {
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(userModel,userAccount);
        return  userAccount;
    }

    private  UserModel convertFromDataObject(UserAccount userAccount, UserPassword userpassword) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userAccount, userModel);
        if (userpassword != null) {
            userModel.setPassword(userpassword.getPassword());
        }
        return  userModel;
    }
}
