package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.BalanceMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Balance;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class BalanceServiceTest {

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    BalanceMapper balanceMapper;

    @Autowired
    BalanceService balanceService;



    public Integer initUser() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("Griffin");
        userAccount.setPhoneNumber("phone");
        //userAccount.setUserId((Integer)123);
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
        assertEquals(ErrorEnum.USER_NOT_EXIST, balanceService.addBalance(999, (float) 1.0));
    }

    @Test
    void addBalanceNewUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);
        assertEquals((float)10.0, balanceMapper.selectByPrimaryKey(userid).getBalance());
    }

    @Test
    void addBalanceExistingUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);
        balanceService.addBalance(userid, (float) 10.0);
        assertEquals((float)20.0, balanceMapper.selectByPrimaryKey(userid).getBalance());
    }

    @Test
    void addBalanceInvalidAmountTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);

        assertEquals(ErrorEnum.INVALID_AMOUNT, balanceService.addBalance(userid, (float) -10));
    }

    //test for subBalance

    @Test
    void subBalanceInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        assertEquals(ErrorEnum.USER_NOT_EXIST, balanceService.subBalance(999, (float) 1.0));
    }

    @Test
    void subBalanceNewUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.subBalance(userid, (float) 0);
        assertEquals((float)0, balanceMapper.selectByPrimaryKey(userid).getBalance());
    }

    @Test
    void subBalanceExistingUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);
        balanceService.subBalance(userid, (float) 5.0);
        assertEquals((float)5.0, balanceMapper.selectByPrimaryKey(userid).getBalance());
    }

    @Test
    void subBalanceInvalidAmountTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);
        assertEquals(ErrorEnum.INVALID_AMOUNT, balanceService.subBalance(userid, (float) -10));
    }

    @Test
    void subBalanceInsufficientTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addBalance(userid, (float) 10.0);
        assertEquals(ErrorEnum.BALANCE_NOT_ENOUGH, balanceService.subBalance(userid, (float) 20));
    }

    //tests for  add quickpass
    @Test
    void addQuickPassInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        assertEquals(ErrorEnum.USER_NOT_EXIST, balanceService.addQuickPass(999, 1));
    }

    @Test
    void addQuickPassNewUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 1);
        assertEquals(4, balanceMapper.selectByPrimaryKey(userid).getQuickpass());
    }

    @Test
    void addQuickpassExistingUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 1);
        balanceService.addQuickPass(userid, 3);
        assertEquals(7, balanceMapper.selectByPrimaryKey(userid).getQuickpass());
    }

    @Test
    void addQuickPassInvalidAmountTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 1);

        assertEquals(ErrorEnum.INVALID_AMOUNT, balanceService.addQuickPass(userid, -10));
    }

    //tests for subQuickPass
    @Test
    void subQuickPassInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        assertEquals(ErrorEnum.USER_NOT_EXIST, balanceService.subQuickPass(999, 1));
    }

    @Test
    void subQuickPassNewUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.subQuickPass(userid, 0);
        assertEquals(3, balanceMapper.selectByPrimaryKey(userid).getQuickpass());
    }

    @Test
    void subQuickPassExistingUserTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 1);
        balanceService.subQuickPass(userid, 1);
        assertEquals(3, balanceMapper.selectByPrimaryKey(userid).getQuickpass());
    }

    @Test
    void subQuickPassInvalidAmountTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 1);
        assertEquals(ErrorEnum.INVALID_AMOUNT, balanceService.subQuickPass(userid, -10));
    }

    @Test
    void subQuickPassInsufficientTest() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 2);
        assertEquals(ErrorEnum.BALANCE_NOT_ENOUGH, balanceService.subQuickPass(userid, 10));
    }


    @Test
    void queryInvalidUserTest() {
        userAccountMapper.deleteByPrimaryKey(999);
        removeBalance(999);
        try {
            balanceService.queryBalance(999);
        } catch (BusinessException err) {
            assertEquals(err.getErrCode(), ErrorEnum.USER_NOT_EXIST.getErrCode());
        }
    }

    @Test
    void queryNewUserTest() throws BusinessException {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.queryBalance(userid);
        assertEquals(3, balanceMapper.selectByPrimaryKey(userid).getQuickpass());
    }

    @Test
    void queryExistingUserTest() throws BusinessException {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        if (userAccountMapper.selectByExample(example).size() > 0) {
            Integer userid = userAccountMapper.selectByExample(example).get(0).getUserId();
            balanceMapper.deleteByPrimaryKey(userid);
            removeUser();
        }
        Integer userid = initUser();
        balanceService.addQuickPass(userid, 0);
        assertEquals(3, balanceService.queryBalance(userid).getQuickpass());
        assertEquals(0, balanceService.queryBalance(userid).getBalance());
    }

}