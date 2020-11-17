package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.TicketService;
import com.java.rollercoaster.service.UserService;
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

import java.text.ParseException;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/ticket")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    UserService userService;

    /**
     * End point to add a ticket given a ticket object.
     * @param ticket a ticket obejct containing information of the ticket you try to add
     * @return CommonReturnType
     * @throws BusinessException a BusinessException object
     */
    @PostMapping("/addTicket")
    @ResponseBody
    public CommonReturnType addTicket(@RequestBody Ticket ticket)
            throws BusinessException, ParseException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        ticket.setUserId(userModel.getUserId());

        //生成ticketID,然后再setTicketID
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String timestamp = String.valueOf(millis);
        String id = timestamp.substring(timestamp.length() - 8)
                + String.format("%02d", end2);
        System.out.println(id);
        ticket.setTicketId(id);
        return CommonReturnType.autoCreate(ticketService.addTicket(ticket));
    }

    /**
     * End point to update a ticket given a ticket object.
     * @param ticket a ticket object containing information try to update
     * @return CommonReturnType
     * @throws BusinessException a BusinessException object
     */
    @PostMapping("/updateTicket")
    @ResponseBody
    public CommonReturnType updateTicket(@RequestBody Ticket ticket) throws BusinessException {
        System.out.println(ticket.toString());
        //only manager can update the tickets status
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }

        if (userModel.getRole().equals(Role.visitor)) {
            throw new BusinessException(ErrorEnum.NO_AUTHORIZATION);
        }
        return CommonReturnType.autoCreate(ticketService.updateTicket(ticket));
    }

    /**
     * End point to delete a ticket given ticketId.
     * @param ticketId the ticketId of the ticket try to delete
     * @return CommonReturnType
     * @throws BusinessException a BusinessException obejct
     */
    @PostMapping("/deleteTicket")
    @ResponseBody
    public CommonReturnType deleteTicket(@RequestParam(name = "ticketId") String ticketId)
            throws BusinessException {
        System.out.println(ticketId);
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        return CommonReturnType.autoCreate(ticketService.deleteTicket(ticketId, userModel));
    }

    /**
     *Display user's history tickets records.
     * @return response with common return type
     * @throws BusinessException exception handler
     */
    @RequestMapping("/ticketsRecord")
    @ResponseBody
    public CommonReturnType getTickets() throws BusinessException {
        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if (!isLogin)  {
            throw new BusinessException(ErrorEnum.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) httpServletRequest
                .getSession().getAttribute("LOGIN_USER");
        //if user not exist
        if (userModel == null) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }
        List<Ticket> ticketList = ticketService.getTicketsByUserId(userModel.getUserId());
        return CommonReturnType.create(ticketList);
    }
}
