package com.java.rollercoaster.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.java.rollercoaster.controller.viewObject.UserVO;
import com.java.rollercoaster.errorEnum.BusinessException;
import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.enumeration.Role;
import com.java.rollercoaster.pojo.enumeration.UserGender;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.UUID.randomUUID;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{
    @Autowired
    UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/login", method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if (org.apache.commons.lang3.StringUtils.isEmpty(telphone) ||
                org.apache.commons.lang3.StringUtils.isEmpty(password)) {
            throw new BusinessException(ErrorEnum.PARAMETER_VALIDATION_ERROR);
        }
        //Determine if login is valid
        UserModel userModel = userService.validateLogin(telphone, this.EncodeByMd5(password));
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

    //Register endpoint
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") String gender,
                                     @RequestParam(name = "age")Integer age,
                                     @RequestParam(name = "password")String password
    ) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //用户的注册流程
        UserModel userModel = new UserModel();
        userModel.setUserName(name);
        userModel.setUserGender(UserGender.valueOf(gender));
        userModel.setUserAge(age);
        userModel.setPhoneNumber(telphone);
        userModel.setPassword(this.EncodeByMd5(password));
        userModel.setRole(Role.visitor);
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //determine computation method
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //encrypt
        String newstr = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return  newstr;
    }


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

    @RequestMapping("/ticketsRecord")
    @ResponseBody
    public CommonReturnType getTickets() throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        List<Ticket> ticketList = userService.getTicketsByUserId(userModel.getUserId());
        return CommonReturnType.create(ticketList);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if(userModel == null) {
            return  null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }
    @RequestMapping(value = "/googleVerify", method = RequestMethod.POST)
    public void verifyToken(String idtokenstr) {
        System.out.println(idtokenstr);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList("66670440653-9ooesmgkcr05a37k224mr3sjsctis262.apps.googleusercontent.com")).build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(idtokenstr);
        } catch (GeneralSecurityException e) {
            System.out.println("驗證時出現GeneralSecurityException異常");
        } catch (IOException e) {
            System.out.println("驗證時出現IOException異常");
        }
        if (idToken != null) {
            System.out.println("驗證成功.");
            GoogleIdToken.Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
			System.out.println("User ID: " + userId);
//			String email = payload.getEmail();
//			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
//			String name = (String) payload.get("name");
//			String pictureUrl = (String) payload.get("picture");
//			String locale = (String) payload.get("locale");
//			String familyName = (String) payload.get("family_name");
//			String givenName = (String) payload.get("given_name");
        } else {
            System.out.println("Invalid ID token.");
        }
    }


}
