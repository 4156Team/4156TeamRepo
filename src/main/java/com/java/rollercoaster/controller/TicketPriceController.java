package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.ManageParkService;
import com.java.rollercoaster.service.TicketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ticketPrice")
public class TicketPriceController {
    @Autowired
    private TicketPriceService ticketPriceService;

    @PostMapping("/getTicketPrice")
    @ResponseBody
    public CommonReturnType getTicketPrice(@RequestBody Type type) {
        if (null == type.getTicketType()) {
            return CommonReturnType.create(ErrorEnum.EMPTY_TYPE_ATTRIBUTE, "fail");
        }
        float price = ticketPriceService.getTicketPrice(type.getTicketType());
        return CommonReturnType.create(price, "success");
    }
}
