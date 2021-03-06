package com.java.rollercoaster.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;





@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;


    /**
     * Log in endpoint.
     * @param telephone user telephone number
     * @param password user password
     * @return return a common response
     * @throws BusinessException exception related invalid parameter
     * @throws UnsupportedEncodingException exception related to encrypt password
     * @throws NoSuchAlgorithmException exception related to encrypt password
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST},
                    consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telephone") String telephone,
                                  @RequestParam(name = "password")String password)
                            throws BusinessException,
                            UnsupportedEncodingException, NoSuchAlgorithmException {
        //parameter verification
        //Determine if login is valid
        UserModel userModel = userService.validateLogin(telephone, this.encodeByMd5(password));
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        Role role = userModel.getRole();
        return CommonReturnType.create(role);
    }

    /**
     * Register endpoint.
     * @param telephone user telephone number
     * @param name user account name
     * @param gender user gender
     * @param age user age
     * @param password user account password
     * @return return a common response
     * @throws BusinessException exception related invalid parameter
     * @throws UnsupportedEncodingException exception related to encrypt password
     * @throws NoSuchAlgorithmException exception related to encrypt password
     */
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telephone") String telephone,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") String gender,
                                     @RequestParam(name = "age")Integer age,
                                     @RequestParam(name = "password")String password,
                                     @RequestParam(name = "email")String email
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UserModel userModel = new UserModel();
        userModel.setUserName(name);
        userModel.setUserGender(UserGender.valueOf(gender));
        userModel.setUserAge(age);
        userModel.setPhoneNumber(telephone);
        userModel.setPassword(this.encodeByMd5(password));
        userModel.setRole(Role.visitor);
        userModel.setEmail(email);
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    /**
     * Generate encrypt password by Md5.
     * @param str input unencrypted password
     * @return an encrypted password
     * @throws NoSuchAlgorithmException exception related to encoding
     * @throws UnsupportedEncodingException exception related to encrypt password
     */
    public String encodeByMd5(String str) throws
            NoSuchAlgorithmException, UnsupportedEncodingException {
        //determine computation method
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //encrypt
        return base64Encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }




    /**
     * Google login.
     * @param userModel userModel
     * @return CommonReturnType
     * @throws BusinessException BusinessException
     */
    @RequestMapping(value = "/googleLogin", method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType googleLogIn(@RequestBody UserModel userModel)
            throws BusinessException {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(userModel.getUserName());
        userAccount.setEmail(userModel.getEmail());
        userAccount.setThirdPartyId(userModel.getThirdPartyId());
        if (userAccount.getThirdPartyId() != null) {
            userModel = userService.loginWithGoogle(userAccount);
            this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
            this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        } else {
            System.out.println("Google Verification Failed");
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        return CommonReturnType.autoCreate(null);
    }

}
