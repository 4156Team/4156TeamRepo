package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.UserAccountMapper;
import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.TicketExample;
import com.java.rollercoaster.service.BalanceService;
import com.java.rollercoaster.service.TicketService;
import com.java.rollercoaster.service.model.UserModel;
import com.java.rollercoaster.service.model.enumeration.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private BalanceService balanceService;


    @Override
    public String addTicket(Ticket ticket, int userId) throws ParseException, BusinessException {
        if (null == ticket) {
            throw new BusinessException(ErrorEnum.EMPTY_TICKET);
        } else if (null != ticketMapper.selectByPrimaryKey(ticket.getTicketId())) {
            throw new BusinessException(ErrorEnum.DUPLICATE_TICKET);
        } else if (null == userAccountMapper.selectByPrimaryKey(ticket.getUserId())) {
            throw new BusinessException(ErrorEnum.USER_NOT_EXIST);
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.parse(sdf.format(date));
        //int days = (int) date.getTime() / 1000 / 3600 / 24;
        Date ticketDate = sdf.parse(sdf.format(ticket.getValidDate()));

        if (ticketDate.before(date)) {
            //You cannot buy past date ticket.
            throw new BusinessException(ErrorEnum.DATE_PASSED);
        }
        if (balanceService.queryBalance(userId).getBalance() < ticket.getPrice()) {
            throw new BusinessException(ErrorEnum.BALANCE_NOT_ENOUGH);
        }
        ticketMapper.insert(ticket);
        balanceService.subBalance(userId, ticket.getPrice());
        return ticket.getTicketId();

    }

    @Override
    public ErrorEnum updateTicket(Ticket ticket) {
        if (null == ticket) {
            return ErrorEnum.EMPTY_TICKET;
        } else if (null == ticketMapper.selectByPrimaryKey(ticket.getTicketId())) {
            return ErrorEnum.NO_SUCH_TICKET;
        } else if (null == userAccountMapper.selectByPrimaryKey(ticket.getUserId())) {
            return ErrorEnum.USER_NOT_EXIST;
        }
        ticketMapper.updateByPrimaryKeySelective(ticket);
        return ErrorEnum.OK;
    }

    @Override
    public ErrorEnum deleteTicket(String ticketId, UserModel userModel) {
        if (null == ticketId) {
            return ErrorEnum.EMPTY_TICKET;
        } else if (null == ticketMapper.selectByPrimaryKey(ticketId)) {
            return ErrorEnum.NO_SUCH_TICKET;
        } else if (userModel.getRole() == Role.visitor && !userModel.getUserId()
                .equals(ticketMapper
                        .selectByPrimaryKey(ticketId).getUserId()) ) {
            return ErrorEnum.NOT_SAME_VISITOR;
        }
        balanceService.addBalance(userModel.getUserId(),
                Optional.ofNullable(ticketMapper.selectByPrimaryKey(ticketId).getPrice())
                        .orElse(0f));
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
