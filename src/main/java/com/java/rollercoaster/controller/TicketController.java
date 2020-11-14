package com.java.rollercoaster.controller;

import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/addTicket")
    @ResponseBody
    public CommonReturnType addTicket(@RequestBody Ticket ticket){
        System.out.println(ticket.toString());
        return CommonReturnType.autoCreate(ticketService.addTicket(ticket));
    }

    @PostMapping("/updateTicket")
    @ResponseBody
    public CommonReturnType updateTicket(@RequestBody Ticket ticket){
        System.out.println(ticket.toString());
        return CommonReturnType.autoCreate(ticketService.updateTicket(ticket));
    }

    @PostMapping("/deleteTicket")
    @ResponseBody
    public CommonReturnType deleteTicket(@RequestParam(name = "ticketId") String ticketId){
        System.out.println(ticketId);
        return CommonReturnType.autoCreate(ticketService.deleteTicket(ticketId));
    }
}
