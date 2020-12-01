package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AppointmentService;
import com.java.rollercoaster.service.QuickPassService;
import com.java.rollercoaster.service.model.TimedAppointmentModel;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/quickpass")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class QuickPassController {
    @Autowired
    QuickPassService quickPassService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * End point to add a new appointment given appointment object.
     * @param quickPass an QuickPass object
     * @return CommonReturnType
     * @throws BusinessException a BusinessException object
     */
    @PostMapping("/appointQuickPass")
    @ResponseBody
    public CommonReturnType addQuickPass(@RequestBody QuickPass quickPass) {
        Boolean isLogin = (Boolean) httpServletRequest
                .getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin)  {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        quickPass.setUserId(userModel.getUserId());
        long millis = System.currentTimeMillis();
        Random random = new Random();
        int end4 = random.nextInt(9999);
        String timestamp = String.valueOf(millis);
        String id = timestamp.substring(timestamp.length() - 6)
                + String.format("%04d", end4);
        System.out.println(id);

        quickPass.setQuickpassId(id);
        try {
            CommonReturnType result =
                    CommonReturnType.create(quickPassService.addQuickPass(quickPass));
            return result;
        } catch (BusinessException businessException) {
            return CommonReturnType.autoCreate((ErrorEnum) businessException.getCommonError());

        }
    }

    /**
     * End point to delete an quickpass appointment.
     * @param quickPassId the quickPassId
     * @return CommonReturnType
     */
    @PostMapping("/deleteQuickPass")
    @ResponseBody
    public CommonReturnType deleteQuickPass(@RequestParam(name = "quickPassId")
                                                        String quickPassId) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin)  {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        return CommonReturnType.autoCreate(quickPassService
                .deleteQuickPass(quickPassId, userModel));
    }

    /**
     *Display a user's all quickPass appointment.
     * @return response with common return type
     */
    @RequestMapping("/quickPassRecord")
    @ResponseBody
    public CommonReturnType getQuickPass(@RequestParam(name = "userId")
                                                     Integer userId) {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin)  {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            return CommonReturnType.autoCreate(ErrorEnum.USER_NOT_EXIST);
        }
        List<QuickPass> quickPassList = quickPassService.getQuickPassByUserId(userId);
        return CommonReturnType.create(quickPassList);
    }
}
