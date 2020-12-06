package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.FacilityMapper;
import com.java.rollercoaster.dao.QuickPassMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.pojo.QuickPassExample;
import com.java.rollercoaster.service.AppointmentService;
import com.java.rollercoaster.service.QuickPassService;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuickPassServiceImpl implements QuickPassService {
    @Autowired
    QuickPassMapper quickPassMapper;
    @Autowired
    FacilityMapper facilityMapper;
    @Autowired
    UserAccountMapper userAccountMapper;

    @Override
    public String addQuickPass(QuickPass quickPass) throws BusinessException {
        if (null != quickPassMapper.selectByPrimaryKey(quickPass
                .getQuickpassId())) {
            throw new BusinessException(ErrorEnum.DUPLICATE_QUICKPASS);
        } else if (null == facilityMapper.selectByPrimaryKey(quickPass.getFacilityName())) {
            throw new BusinessException(ErrorEnum.NO_SUCH_FACILITY);
        } else if (null == userAccountMapper.selectByPrimaryKey(quickPass.getUserId())) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        QuickPassExample example = new QuickPassExample();
        example.createCriteria().andUserIdEqualTo(quickPass.getUserId());
        if (quickPassMapper.selectByExample(example).size() >= 2) {
            throw new BusinessException(ErrorEnum.OVER_QUICKPASS_LIMIT);
        }
        quickPassMapper.insert(quickPass);
        return quickPass.getQuickpassId();
    }

    @Override
    public ErrorEnum deleteQuickPass(String quickPassId, UserModel userModel) {
        if (quickPassMapper.selectByPrimaryKey(quickPassId) == null) {
            return ErrorEnum.QUICKPASS_NOT_EXIST;
        }
        if (null == userAccountMapper.selectByPrimaryKey(userModel.getUserId())) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        if ((userModel.getUserId().intValue()
                != quickPassMapper.selectByPrimaryKey(quickPassId).getUserId().intValue())
            && (userAccountMapper.selectByPrimaryKey(userModel.getUserId()).getRole()
                != Role.manager)) {
            return ErrorEnum.NOT_SAME_VISITOR;
        }
        quickPassMapper.deleteByPrimaryKey(quickPassId);
        return ErrorEnum.OK;
    }

    @Override
    public List<QuickPass> getQuickPassByUserId(Integer userId) {
        QuickPassExample example = new QuickPassExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return quickPassMapper.selectByExample(example);

    }

}
