package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.BalanceMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Balance;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.BalanceService;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
class BalanceControllerTest {

    @Autowired
    BalanceController balanceController;
    @Autowired
    BalanceService balanceService;
    @Autowired
    UserAccountMapper userAccountMapper;
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public Integer initUser() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setUserName("Alice");
        userModel.setUserGender(UserGender.female);
        userModel.setRole(Role.visitor);
        userModel.setPhoneNumber("212121");
        userModel.setPassword("12345");
        userService.register(userModel);

        httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return userModel.getUserId();
    }

    public void initBalance(Integer userid) {
        Balance balance = new Balance();
        balance.setUserId(userid);
        balance.setQuickpass(3);
        balance.setBalance((float) 0);
        balanceMapper.insert(balance);
    }

    public void removeUser() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("212121");
        userAccountMapper.deleteByExample(example);
    }


    public void removeBalance(Integer userid) {
        balanceMapper.deleteByPrimaryKey(userid);
    }

    @Test
    void addBalanceTest() throws BusinessException {
        removeUser();
        Integer userId = initUser();
        initBalance(userId);
        CommonReturnType rs =  balanceController.addBalance((float) 1.0);
        assertEquals(ErrorEnum.OK, (ErrorEnum)rs.getData());
        removeUser();
        removeBalance(userId);
    }

    @Test
    void subBalanceTest() throws BusinessException {
        removeUser();
        Integer userId = initUser();
        initBalance(userId);
        CommonReturnType rs =  balanceController.subBalance((float) 0);
        assertEquals(ErrorEnum.OK, (ErrorEnum)rs.getData());
        removeUser();
        removeBalance(userId);
    }

    @Test
    void addQuickPassInvalidUserTest() throws BusinessException {
        removeUser();
        Integer userId = initUser();
        initBalance(userId);
        CommonReturnType rs =  balanceController.addQuickPass(1);
        assertEquals(ErrorEnum.OK, (ErrorEnum)rs.getData());
        removeUser();
        removeBalance(userId);
    }

    @Test
    void subQuickPassInvalidUserTest() throws BusinessException {
        removeUser();
        Integer userId = initUser();
        initBalance(userId);
        CommonReturnType rs =  balanceController.subQuickPass(0);
        assertEquals(ErrorEnum.OK, (ErrorEnum)rs.getData());
        removeUser();
        removeBalance(userId);
    }

    @Test
    void queryBalanceTest1() throws BusinessException {
        removeUser();
        Integer userId = initUser();
        initBalance(userId);
        balanceController.addBalance((float) 10);
        balanceController.addQuickPass(3);
        CommonReturnType rs =  balanceController.queryBalance();
        assertEquals((float)10.0,((Balance)rs.getData()).getBalance());
        assertEquals(6,((Balance)rs.getData()).getQuickpass());
        removeUser();
        removeBalance(userId);
    }

    @Test
    void queryBalanceTest2() throws BusinessException {
        removeUser();
        Integer userId = initUser();
        removeUser();
        initBalance(userId);
        balanceController.addBalance((float) 10);
        balanceController.addQuickPass(3);
        CommonReturnType rs =  balanceController.queryBalance();
        assertEquals(ErrorEnum.USER_NOT_EXIST,((ErrorEnum)rs.getData()));
        removeBalance(userId);
    }

}
