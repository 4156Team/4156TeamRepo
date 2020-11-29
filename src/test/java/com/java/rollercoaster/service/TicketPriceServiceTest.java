package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.TypeMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TicketPriceServiceTest {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private TicketPriceService ticketPriceService;

    @Test
    public void testGetTicketPrice() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        typeMapper.insert(type);

        assertEquals(50, ticketPriceService.getTicketPrice(TicketType.adult));

        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testChangeTicketPrice() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        typeMapper.insert(type);

        type.setTicketPrice(10f);
        assertEquals(ErrorEnum.OK, ticketPriceService.changeTicketPrice(type));
        assertEquals(10, typeMapper.selectByPrimaryKey(TicketType.adult).getTicketPrice());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }

    @Test
    public void testChangeTicketPriceFail() {
        Type type = new Type();
        type.setTicketType(TicketType.adult);
        type.setTicketPrice(50f);
        typeMapper.insert(type);

        type.setTicketPrice(null);
        assertEquals(ErrorEnum.EMPTY_TYPE_ATTRIBUTE, ticketPriceService.changeTicketPrice(type));
        assertEquals(50, typeMapper.selectByPrimaryKey(TicketType.adult).getTicketPrice());
        typeMapper.deleteByPrimaryKey(TicketType.adult);
    }
}
