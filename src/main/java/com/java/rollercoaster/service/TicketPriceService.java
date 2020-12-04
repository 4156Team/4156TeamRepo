package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.service.model.enumeration.TicketType;

import java.util.List;

public interface TicketPriceService {
    float getTicketPrice(TicketType ticketType);

    ErrorEnum changeTicketPrice(Type type);

    List<Type> getAllTicketPrice();
}
