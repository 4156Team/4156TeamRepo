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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @PostMapping("/addTicket")
    @ResponseBody
    public CommonReturnType addTicket(@RequestBody Ticket ticket) throws BusinessException {
        System.out.println(ticket.toString());
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
        //这里加一下生成ticketID,然后再setTicketID


        return CommonReturnType.autoCreate(ticketService.addTicket(ticket));
    }

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

    @PostMapping("/deleteTicket")
    @ResponseBody
    public CommonReturnType deleteTicket(@RequestParam(name = "ticketId") String ticketId){
        System.out.println(ticketId);
        return CommonReturnType.autoCreate(ticketService.deleteTicket(ticketId));
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
