package com.java.rollercoaster.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.dao.UserPasswordMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.UserAccount;
import com.java.rollercoaster.pojo.UserAccountExample;
import com.java.rollercoaster.pojo.UserPassword;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.UserService;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import com.java.rollercoaster.service.model.enumeration.UserGender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private UserPasswordMapper userPasswordMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;

    private int id;
    private MessageDigest md5;
    private BASE64Encoder base64Encoder = new BASE64Encoder();

    public void init() throws NoSuchAlgorithmException {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserGender(UserGender.male);
        userAccount.setUserName("test");
        userAccount.setUserAge(20);
        userAccount.setRole(Role.visitor);
        userAccount.setPhoneNumber("118118118");
        userAccountMapper.insert(userAccount);
        id = userAccount.getUserId();
        UserPassword userPassword = new UserPassword();
        md5 = MessageDigest.getInstance("MD5");
        userPassword.setPassword(base64Encoder.encode(md5.digest("1234".getBytes(StandardCharsets.UTF_8))));
        userPassword.setUserId(id);
        userPasswordMapper.insert(userPassword);

    }

    @Test
    public void testLogIn() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        init();
        CommonReturnType commonReturnType = userController.login("118118118", "1234");
        assertEquals("success", commonReturnType.getStatus());
        assertEquals(Role.visitor, commonReturnType.getData());
        finish();
    }

    @Test
    public void testRegister() throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        init();
        CommonReturnType commonReturnType = userController.register(
                "120120120", "m", "male", 29, "1234","test@gmail.com"
        );
        assertEquals("success", commonReturnType.getStatus());
        UserAccountExample example = new UserAccountExample();
        UserAccountExample.Criteria criteria = example.createCriteria();
        criteria.andPhoneNumberEqualTo("120120120");
        UserAccount userAccount = userAccountMapper.selectByExample(example).get(0);
        assertEquals("120120120", userAccount.getPhoneNumber());
        assertEquals(29, userAccount.getUserAge());
        assertEquals("m", userAccount.getUserName());
        assertEquals(UserGender.male, userAccount.getUserGender());
        assertEquals(base64Encoder.encode(md5.digest("1234".getBytes(StandardCharsets.UTF_8))), userPasswordMapper.selectByPrimaryKey(userAccount.getUserId()).getPassword());
        userAccountMapper.deleteByPrimaryKey(userAccount.getUserId());
        userPasswordMapper.deleteByPrimaryKey(userAccount.getUserId());
        finish();
    }

    @Test
    public void testEncodeMD5() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        init();
        assertEquals(base64Encoder.encode(md5.digest("1234".getBytes(StandardCharsets.UTF_8))),
                userController.encodeByMd5("1234"));
        finish();
    }

    @Test
    public void validGoogleLoginTest() throws BusinessException {
        UserModel userModel = new UserModel();
        userModel.setEmail("aaaa@columbia.edu");
        userModel.setUserName("Yumiao");
        userModel.setThirdPartyId("asdsfgh");
        userController.googleLogIn(userModel);
        UserModel userModel1 = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        assertEquals(userModel.getUserName(), userModel1.getUserName());
        assertEquals(true, httpServletRequest.getSession().getAttribute("IS_LOGIN"));
        userAccountMapper.deleteByPrimaryKey(userModel1.getUserId());

//        UserAccountExample userAccountExample = new UserAccountExample();
//        UserAccountExample.Criteria criteria = userAccountExample.createCriteria();
//        criteria.andEmailEqualTo("yl4225@columbia.edu");
//        UserAccount userAccount = userAccountMapper.selectByExample(userAccountExample).get(0);
//        userAccountMapper.deleteByPrimaryKey(userAccount.getUserId());
    }

    @Test
    public void googleLoginVerificationFailedTest() {
        UserModel userModel = new UserModel();
        userModel.setEmail("yl4225@columbia.edu");
        userModel.setUserName("Yumiao");
        try {
            userController.googleLogIn(userModel);
        } catch (BusinessException businessException) {
            assertEquals(ErrorEnum.PARAMETER_VALIDATION_ERROR, businessException.getCommonError());
        }




    }

//    @Test
//    public void testVerifyToken() throws
//            NoSuchFieldException, IllegalAccessException,
//            GeneralSecurityException, IOException, BusinessException {
////        GoogleIdTokenVerifier verifier = PowerMockito.mock(GoogleIdTokenVerifier.class);
////        GoogleIdToken googleIdToken = PowerMockito.mock(GoogleIdToken.class);
////        PowerMockito.when(payload.getSubject()).thenReturn("testGoogleVerify");
////        PowerMockito.when(payload.get("name")).thenReturn("KD");
////        PowerMockito.when(googleIdToken.getPayload()).thenReturn(payload);
////        PowerMockito.when(verifier.verify(Mockito.any(String.class))).thenReturn(googleIdToken);
////
////        Field field = controller.getClass().getField("userService");
////        field.setAccessible(false);
////        field.set(controller, service);
////        UserController controller = Mockito.spy(new UserController());
////        UserAccount userAccount = new UserAccount();
////        userAccount.setThirdPartyId("testGoogleVerify");
////        userAccount.setUserName("testGoogle");
////        Mockito.doReturn(userAccount).when(controller).googleVerifyApi(Mockito.anyString());
////        Mockito.doReturn(new UserModel()).when(service).loginWithGoogle(Mockito.any());
////        controller.verifyToken("testToken");
////
////        UserAccountExample example = new UserAccountExample();
////        UserAccountExample.Criteria criteria = example.createCriteria();
////        criteria.andThirdPartyIdEqualTo("testGoogleVerify");
////        UserAccount userAccount1 = userAccountMapper.selectByExample(example).get(0);
////        assertEquals("testGoogle", userAccount1.getUserName());
////        userAccountMapper.deleteByPrimaryKey(userAccount1.getUserId());
//        userController.verifyToken("12313123");
//
//    }

    public void finish() {
        userPasswordMapper.deleteByPrimaryKey(id);
        userAccountMapper.deleteByPrimaryKey(id);
    }
}
