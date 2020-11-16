package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;


public class BaseController {
    //handle exceptions which are not handled by controller layer
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    /**
     * Handle exception which are not processed in controller layer.
     * @param request http request
     * @param ex exception
     * @return a response with defined common type
     */
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerException(HttpServletRequest request, Exception ex) {
//        Map<String, Object> responseData = new HashMap<>();
//        if (ex instanceof BusinessException) {
//            BusinessException businessException = (BusinessException) ex;
//            responseData.put("errCode", businessException.getErrCode());
//            responseData.put("errMsg", businessException.getErrMsg());
//        } else {
//            responseData.put("errCode", ErrorEnum.UNKNOWN_ERROR.getErrCode());
//            responseData.put("errMsg", ErrorEnum.UNKNOWN_ERROR.getErrMsg());
//        }
//        return CommonReturnType.create(responseData,"fail");
//
//    }

}
