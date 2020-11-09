package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    //handle exceptions which are not handled by controller layer
    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerExceotion(HttpServletRequest request, Exception ex) {
//        Map<String, Object> responseData = new HashMap<>();
//        if (ex instanceof BusinessException) {
//            BusinessException businessException = (BusinessException) ex;
//            responseData.put("errCode", businessException.getErrCode());
//            responseData.put("errMsg", businessException.getErrMsg());
//        }else {
//            responseData.put("errCode", ErrorEnum.UNKNOWN_ERROR.getErrCode());
//            responseData.put("errMsg", ErrorEnum.UNKNOWN_ERROR.getErrMsg());
//        }
//        return CommonReturnType.create(responseData,"fail");
//
//    }

}
