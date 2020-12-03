package com.java.rollercoaster.service;


import com.java.rollercoaster.dao.BalanceMapper;
import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.QuickPassMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.*;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class QuickPassServiceTest {

    @Autowired
    UserAccountMapper userAccountMapper;

    @Autowired
    QuickPassMapper quickPassMapper;

    @Autowired
    QuickPassService quickPassService;

    @Autowired
    FacilityMapper facilityMapper;


    public Integer initUser() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("Griffin");
        userAccount.setPhoneNumber("phone");
        //userAccount.setUserId((Integer)123);
        userAccount.setRole(Role.visitor);
        userAccountMapper.insert(userAccount);
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        return userAccountMapper.selectByExample(example).get(0).getUserId();
    }

    public Integer initUser1() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("Griffin1");
        userAccount.setPhoneNumber("phone1");
        //userAccount.setUserId((Integer)123);
        userAccount.setRole(Role.visitor);
        userAccountMapper.insert(userAccount);
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone1");
        return userAccountMapper.selectByExample(example).get(0).getUserId();
    }

    public void initFacility() {
        Facility newFacility = new Facility();
        newFacility.setFacilityName("quickpassTestFacility");
        newFacility.setFacilityOpenTime(new Date(1,2,3,4,5,6));
        newFacility.setFacilityCloseTime(new Date(1,1,1,7,8,9));
        newFacility.setFacilityIntroduction("test introduction");
        newFacility.setFacilityStatus(FacilityStatus.normal);

        facilityMapper.insert(newFacility);
    }

    public void removeUser() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone");
        userAccountMapper.deleteByExample(example);
    }

    public void removeUser1() {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria().andPhoneNumberEqualTo("phone1");
        userAccountMapper.deleteByExample(example);
    }

    public void removeFacility() {
        facilityMapper.deleteByPrimaryKey("quickpassTestFacility");
    }

    @Test
    public void addQCTest0() throws BusinessException {
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("facility");
        quickPass.setUserId(1);
        quickPass.setStartTime(new Date());
        quickPassMapper.insert(quickPass);
        try {
            quickPassService.addQuickPass(quickPass);
        } catch (BusinessException err) {
            assertEquals(err.getErrCode(), 310);
        }
        quickPassMapper.deleteByPrimaryKey("qdid1");
    }

    @Test
    public void addQCTest1() throws BusinessException {
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("facilitynotExist");
        quickPass.setUserId(1);
        quickPass.setStartTime(new Date());
        try {
            quickPassService.addQuickPass(quickPass);
        } catch (BusinessException err) {
            assertEquals(err.getErrCode(), 226);
        }
        quickPassMapper.deleteByPrimaryKey("qdid1");
    }

    @Test
    public void addQCTest2() throws BusinessException {
        removeFacility();
        initFacility();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("quickpassTestFacility");
        quickPass.setUserId(-1);
        quickPass.setStartTime(new Date());
        try {
            quickPassService.addQuickPass(quickPass);
        } catch (BusinessException err) {
            assertEquals(err.getErrCode(), 20001);
        }
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeFacility();
    }

    @Test
    public void addQCTest3() throws BusinessException {
        removeFacility();
        initFacility();
        Integer userId = initUser();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("quickpassTestFacility");
        quickPass.setUserId(userId);
        quickPass.setStartTime(new Date());
        try {
            quickPassService.addQuickPass(quickPass);
        } catch (BusinessException err) {
            assertEquals(err.getErrCode(), 20001);
        }
        assertEquals(1,1);
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeFacility();
    }

    @Test
    public void deleteQCTest0() throws BusinessException {
        removeFacility();
        initFacility();
        Integer userId = initUser();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("quickpassTestFacility");
        quickPass.setUserId(userId);
        quickPass.setStartTime(new Date());
        String qcid = quickPassService.addQuickPass(quickPass);
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        assertEquals(quickPassService.deleteQuickPass(qcid, userModel),ErrorEnum.OK);
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeFacility();
    }

    @Test
    public void deleteQCTest1() throws BusinessException {
        removeUser();
        removeFacility();
        initFacility();
        Integer userId = initUser();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        assertEquals(quickPassService.deleteQuickPass("qdid1", userModel),ErrorEnum.QUICKPASS_NOT_EXIST);
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeFacility();
    }

    @Test
    public void deleteQCTest2() throws BusinessException {
        removeUser();
        removeFacility();
        initFacility();
        Integer userId = initUser();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("quickpassTestFacility");
        quickPass.setUserId(userId);
        quickPass.setStartTime(new Date());
        String qcid = quickPassService.addQuickPass(quickPass);
        UserModel userModel = new UserModel();
        userModel.setUserId(-1);
        assertEquals(quickPassService.deleteQuickPass("qdid1", userModel),ErrorEnum.USER_NOT_EXIST);
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeFacility();
    }

    @Test
    public void deleteQCTest3() throws BusinessException {
        removeFacility();
        initFacility();
        Integer userId = initUser();
        Integer userId1 = initUser1();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("quickpassTestFacility");
        quickPass.setUserId(userId);
        quickPass.setStartTime(new Date());
        String qcid = quickPassService.addQuickPass(quickPass);
        UserModel userModel = new UserModel();
        userModel.setUserId(userId1);
        assertEquals(quickPassService.deleteQuickPass("qdid1", userModel),ErrorEnum.NOT_SAME_VISITOR);
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeUser1();
        removeFacility();
    }

    @Test
    public void getQCTest0() throws BusinessException {
        removeFacility();
        initFacility();
        Integer userId = initUser();
        quickPassMapper.deleteByPrimaryKey("qdid1");
        QuickPass quickPass = new QuickPass();
        quickPass.setQuickpassId("qdid1");
        quickPass.setFacilityName("quickpassTestFacility");
        quickPass.setUserId(userId);
        quickPass.setStartTime(new Date());
        String qcid = quickPassService.addQuickPass(quickPass);
        UserModel userModel = new UserModel();
        userModel.setUserId(userId);
        assertNotNull(quickPassService.getQuickPassByUserId(userId));
        quickPassMapper.deleteByPrimaryKey("qdid1");
        removeUser();
        removeFacility();
    }
}
