package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.BalanceService;
import com.java.rollercoaster.service.QueryService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller("balance")
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    BalanceService balanceService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * End point to add balance.
     *
     *
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/addBalance", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType addBalance(@RequestParam(name = "amount") Float amount) {
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        Integer userId = userModel.getUserId();
        return CommonReturnType.autoCreate(balanceService.addBalance(userId, amount));
    }

    /**
     * End point to subtract balance.
     *
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/subBalance", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType subBalance(@RequestParam(name = "amount") Float amount) {
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        Integer userId = userModel.getUserId();
        return CommonReturnType.autoCreate(balanceService.subBalance(userId, amount));
    }

    /**
     * End point to add quickpass.
     *
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/addQuickPass", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType addQuickPass(@RequestParam(name = "amount") int amount) {
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        Integer userId = userModel.getUserId();
        return CommonReturnType.autoCreate(balanceService.addQuickPass(userId, amount));
    }

    /**
     * End point to subtract quickpass.
     *
     * @param  amount        amount
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/subQuickPass", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType subQuickPass(@RequestParam(name = "amount") int amount) {
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        Integer userId = userModel.getUserId();
        return CommonReturnType.autoCreate(balanceService.subQuickPass(userId, amount));
    }

    /**
     * End point to query balance.
     *
     * @return                 a CommonReturnType
     */
    @RequestMapping(value = "/queryBalance", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType queryBalance() {
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        Integer userId = userModel.getUserId();
        try {
            return CommonReturnType.create(balanceService.queryBalance(userId));
        } catch (BusinessException err) {
            return CommonReturnType.autoCreate((ErrorEnum) err.getCommonError());
        }
    }

}
