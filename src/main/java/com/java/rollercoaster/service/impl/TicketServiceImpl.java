package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.TicketExample;
import com.java.rollercoaster.service.TicketService;
import java.util.Date;
import java.util.List;

import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public ErrorEnum addTicket(Ticket ticket) {
        if (null == ticket){
            return ErrorEnum.EMPTY_TICKET;
        } else if (null != ticketMapper.selectByPrimaryKey(ticket.getTicketId())){
            return ErrorEnum.DUPLICATE_TICKET;
        } else if (null == userAccountMapper.selectByPrimaryKey(ticket.getUserId())) {
            return ErrorEnum.USER_NOT_EXIST;
        } else if(!ticket.getValidDate().after(new Date())) {
            return ErrorEnum.DATE_PASSED;
        }
        ticketMapper.insert(ticket);
        return ErrorEnum.OK;

    }

    @Override
    public ErrorEnum updateTicket(Ticket ticket) {
        if (null == ticket){
            return ErrorEnum.EMPTY_TICKET;
        } else if (null == ticketMapper.selectByPrimaryKey(ticket.getTicketId())){
            return ErrorEnum.NO_SUCH_TICKET;
        } else if (null == userAccountMapper.selectByPrimaryKey(ticket.getUserId())){
            return ErrorEnum.USER_NOT_EXIST;
        }
        ticketMapper.updateByPrimaryKeySelective(ticket);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum deleteTicket(String ticketId, UserModel userModel) {
        if (null == ticketId){
            return ErrorEnum.EMPTY_TICKET;
        } else if (null == ticketMapper.selectByPrimaryKey(ticketId)){
            return ErrorEnum.NO_SUCH_TICKET;
        } else if(userModel.getRole() == Role.visitor && userModel.getUserId() !=
                ticketMapper
                        .selectByPrimaryKey(ticketId).getUserId()){
            return ErrorEnum.NOT_SAME_VISITOR;
        }
        ticketMapper.deleteByPrimaryKey(ticketId);
        return ErrorEnum.OK;
    }

    @Override
    public List<Ticket> getTicketsByUserId(Integer userId) {
        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        return ticketList;
    }

}
