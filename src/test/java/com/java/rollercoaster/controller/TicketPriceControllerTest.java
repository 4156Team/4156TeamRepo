package com.java.rollercoaster.controller;

import com.java.rollercoaster.dao.TypeMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.TicketPriceService;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import jdk.nashorn.internal.codegen.TypeMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TicketPriceControllerTest {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private TicketPriceController ticketPriceController;
    @Test
    public void testGetTicketPrice() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        if (null != typeMapper.selectByPrimaryKey(type.getTicketType())){
            typeMapper.updateByPrimaryKey(type);
        } else {
            typeMapper.insert(type);
        }

        Type type1 = new Type();
        type1.setTicketType(TicketType.adult);
        CommonReturnType response = ticketPriceController.getTicketPrice(type1);
        assertEquals("success", response.getStatus());
        assertEquals(50f, response.getData());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testGetTicketPriceFail() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        if (null != typeMapper.selectByPrimaryKey(type.getTicketType())){
            typeMapper.updateByPrimaryKey(type);
        } else {
            typeMapper.insert(type);
        }

        Type type1 = new Type();
        CommonReturnType response = ticketPriceController.getTicketPrice(type1);
        assertEquals("fail", response.getStatus());
        assertEquals(ErrorEnum.EMPTY_TYPE_ATTRIBUTE, response.getData());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }
    @Test
    public void testGetAllPrices() {
        CommonReturnType commonReturnType = ticketPriceController.getAllTicketPrice();
        assertEquals("success", commonReturnType.getStatus());
    }
}
