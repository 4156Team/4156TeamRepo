package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.BalanceService;
import com.java.rollercoaster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("balance")
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;

    /**
     * End point to add balance.
     *
     *
     * @param  userId        userid
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/addBalance", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType addBalance(@RequestParam(name = "userID") Integer userId,
                                       @RequestParam(name = "amount") Float amount) {
        return CommonReturnType.autoCreate(balanceService.addBalance(userId, amount));
    }

    /**
     * End point to subtract balance.
     *
     *
     * @param  userId        userid
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/subBalance", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType subBalance(@RequestParam(name = "userID") Integer userId,
                                       @RequestParam(name = "amount") Float amount) {
        return CommonReturnType.autoCreate(balanceService.subBalance(userId, amount));
    }

    /**
     * End point to add quickpass.
     *
     *
     * @param  userId        userid
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/addQuickPass", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType addQuickPass(@RequestParam(name = "userID") Integer userId,
                                         @RequestParam(name = "amount") int amount) {
        return CommonReturnType.autoCreate(balanceService.addQuickPass(userId, amount));
    }

    /**
     * End point to subtract quickpass.
     *
     *
     * @param  userId        userid
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/subQuickPass", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType subQuickPass(@RequestParam(name = "userID") Integer userId,
                                       @RequestParam(name = "amount") int amount) {
        return CommonReturnType.autoCreate(balanceService.subQuickPass(userId, amount));
    }

    /**
     * End point to query balance.
     *
     *
     * @param  userId        userid
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/queryBalance", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryBalance(@RequestParam(name = "userID") Integer userId) {
        try {
            return CommonReturnType.create(balanceService.queryBalance(userId));
        } catch (BusinessException err) {
            return CommonReturnType.autoCreate((ErrorEnum) err.getCommonError());
        }
    }

}
