package com.java.rollercoaster.controller;

import com.java.rollercoaster.controller.viewObject.UserVO;
import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    UserService userService;
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer userId) throws BusinessException {
        //implement service to get user model
        UserModel userModel = userService.getUserByUserId(userId);
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        //convert data model to view model
        UserVO userVO = convertFromModel(userModel);
        //return common object
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if(userModel == null) {
            return  null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }


}
