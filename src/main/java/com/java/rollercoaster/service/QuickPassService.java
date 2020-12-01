package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;

import java.util.List;

public interface QuickPassService {

    String addQuickPass(QuickPass quickpass) throws BusinessException;
    //return quickpass id

    ErrorEnum deleteQuickPass(String quickpassId, UserModel userModel);

    List<QuickPass> getQuickPassByUserId(Integer userId);
}
