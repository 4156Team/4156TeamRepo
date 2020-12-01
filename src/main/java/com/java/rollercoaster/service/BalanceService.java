package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Balance;

public interface BalanceService {
    ErrorEnum addBalance(Integer userId, float amount);

    ErrorEnum subBalance(Integer userId, float amount);

    ErrorEnum addQuickPass(Integer userId, int amount);

    ErrorEnum subQuickPass(Integer userId, int amount);

    Balance queryBalance(Integer userId) throws BusinessException;
}
