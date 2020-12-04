package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.TicketMapper;
import com.java.rollercoaster.dao.TypeMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.pojo.TypeExample;
import com.java.rollercoaster.service.TicketPriceService;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPriceServiceImpl implements TicketPriceService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public float getTicketPrice(TicketType ticketType) {
        return typeMapper.selectByPrimaryKey(ticketType).getTicketPrice();
    }

    @Override
    public ErrorEnum changeTicketPrice(Type type) {
        if (null == type.getTicketPrice() || null == type.getTicketType()) {
            return ErrorEnum.EMPTY_TYPE_ATTRIBUTE;
        }
        typeMapper.updateByPrimaryKey(type);
        return ErrorEnum.OK;
    }

    @Override
    public List<Type> getAllTicketPrice() {
        return typeMapper.selectByExample(new TypeExample());
    }
}
