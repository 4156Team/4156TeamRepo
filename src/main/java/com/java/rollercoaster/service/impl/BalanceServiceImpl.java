package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.BalanceMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Balance;
import com.java.rollercoaster.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    UserAccountMapper userAccountMapper;

    @Override
    public ErrorEnum addBalance(Integer userId, float amount) {
        if (userAccountMapper.selectByPrimaryKey(userId) == null) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        if (balanceMapper.selectByPrimaryKey(userId) == null) {

            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalance(amount);
            balance.setQuickpass(3);
            balanceMapper.insert(balance);
            return ErrorEnum.OK;
        }
        if (amount < 0) {
            return ErrorEnum.INVALID_AMOUNT;
        }
        Balance balance = balanceMapper.selectByPrimaryKey(userId);
        balance.setBalance(balance.getBalance() + amount);
        balanceMapper.updateByPrimaryKey(balance);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum subBalance(Integer userId, float amount) {
        if (userAccountMapper.selectByPrimaryKey(userId) == null) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        if (balanceMapper.selectByPrimaryKey(userId) == null) {

            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalance((float) 0);
            balance.setQuickpass(3);
            balanceMapper.insert(balance);
            return ErrorEnum.BALANCE_NOT_ENOUGH;
        }
        if (amount < 0) {
            return ErrorEnum.INVALID_AMOUNT;
        }
        Balance balance = balanceMapper.selectByPrimaryKey(userId);
        if (amount > balance.getBalance()) {
            return ErrorEnum.BALANCE_NOT_ENOUGH;
        }
        balance.setBalance(balance.getBalance() - amount);
        balanceMapper.updateByPrimaryKey(balance);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum addQuickPass(Integer userId, int amount) {
        if (userAccountMapper.selectByPrimaryKey(userId) == null) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        if (balanceMapper.selectByPrimaryKey(userId) == null) {

            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalance((float) 0);
            balance.setQuickpass(amount + 3);
            balanceMapper.insert(balance);
            return ErrorEnum.OK;
        }
        if (amount < 0) {
            return ErrorEnum.INVALID_AMOUNT;
        }
        Balance balance = balanceMapper.selectByPrimaryKey(userId);
        balance.setQuickpass(balance.getQuickpass() + amount);
        balanceMapper.updateByPrimaryKey(balance);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum subQuickPass(Integer userId, int amount) {
        if (userAccountMapper.selectByPrimaryKey(userId) == null) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        if (balanceMapper.selectByPrimaryKey(userId) == null) {

            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalance((float) 0);
            balance.setQuickpass(3);
            balanceMapper.insert(balance);
        }
        if (amount < 0) {
            return ErrorEnum.INVALID_AMOUNT;
        }
        Balance balance = balanceMapper.selectByPrimaryKey(userId);
        if (amount > balance.getQuickpass()) {
            return ErrorEnum.BALANCE_NOT_ENOUGH;
        }
        balance.setQuickpass(balance.getQuickpass() - amount);
        balanceMapper.updateByPrimaryKey(balance);
        return ErrorEnum.OK;
    }

    @Override
    public Balance queryBalance(Integer userId) throws BusinessException {
        if (userAccountMapper.selectByPrimaryKey(userId) == null) {
            throw(new BusinessException(ErrorEnum.USER_NOT_EXIST));
        }
        if (balanceMapper.selectByPrimaryKey(userId) == null) {

            Balance balance = new Balance();
            balance.setUserId(userId);
            balance.setBalance((float) 0);
            balance.setQuickpass(3);
            balanceMapper.insert(balance);
            return balance;
        }
        Balance balance = balanceMapper.selectByPrimaryKey(userId);
        return balance;
    }

}
