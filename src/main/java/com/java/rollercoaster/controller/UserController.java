package com.java.rollercoaster.controller;

import com.java.rollercoaster.controller.viewObject.UserVO;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="phoneNumber") String phoneNumber) {
        //implement service to get user model
        UserModel userModel = userService.getUserByPhoneNumber(phoneNumber);
        //if user not exist
        if (userModel == null) {
            return null;
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);

//        //将核心领域模型用户对象转化为可供UI使用的viewObject
//        UserVO userVO = convertFromModel(userModel);
//        //返回通用对象
//        return CommonReturnType.create(userVO);
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
