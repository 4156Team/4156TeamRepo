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
import com.java.rollercoaster.service.model.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    public Integer initUser() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("Griffin");
        userAccount.setPhoneNumber("phone");
        userAccount.setRole(Role.visitor);
        userAccountMapper.insert(userAccount);
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        return userAccountMapper.selectByExample(example).get(0).getUserId();
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
        example.createCriteria().andPhoneNumberEqualTo("phone");
        userAccountMapper.deleteByExample(example);
    }

    public void removeBalance(Integer userid) {
        balanceMapper.deleteByPrimaryKey(userid);
    }

    @Test
    void addBalanceInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        CommonReturnType rs =  balanceController.addBalance(999, (float) 1.0);
        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum)rs.getData());
    }

    @Test
    void subBalanceInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        CommonReturnType rs =  balanceController.subBalance(999, (float) 1.0);
        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum)rs.getData());
    }

    @Test
    void addQuickPassInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        CommonReturnType rs =  balanceController.addQuickPass(999, 1);
        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum)rs.getData());
    }

    @Test
    void subQuickPassInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        CommonReturnType rs =  balanceController.subQuickPass(999, 1);
        assertEquals(ErrorEnum.USER_NOT_EXIST, (ErrorEnum)rs.getData());
    }

    @Test
    void queryBalanceTest1() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);
        CommonReturnType rs =  balanceController.queryBalance(userid);
        assertEquals((float)10.0,((Balance)rs.getData()).getBalance());
    }

    @Test
    void queryBalanceTest2() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        CommonReturnType rs =  balanceController.queryBalance(99999);
        assertEquals(ErrorEnum.USER_NOT_EXIST,(ErrorEnum)rs.getData());
    }
}
