package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorEnum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.CheckInService;
import com.java.rollercoaster.service.ManageParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManageParkService manageParkService;
    @Autowired
    CheckInService checkInService;

    @PostMapping("/addEvent")
    @ResponseBody
    public CommonReturnType addEvent(@RequestBody Event event){
        System.out.println(event.toString());
        return CommonReturnType.autoCreate(manageParkService.addEvent(event));
    }

    @PostMapping("/updateEvent")
    @ResponseBody
    public CommonReturnType updateEvent(@RequestBody Event event){
        return CommonReturnType.autoCreate(manageParkService.updateEvent(event));
    }

    @PostMapping("/deleteEvent")
    @ResponseBody
    public CommonReturnType deleteEvent(@RequestBody String eventName){
        return CommonReturnType.autoCreate(manageParkService.deleteEvent(eventName));
    }

    @PostMapping("/addFacility")
    @ResponseBody
    public CommonReturnType addFacility(@RequestBody Facility facility){
        return CommonReturnType.autoCreate(manageParkService.addFacility(facility));
    }

    @PostMapping("/updateFacility")
    @ResponseBody
    public CommonReturnType updateFacility(@RequestBody Facility facility){
        System.out.println(facility.toString());
        return CommonReturnType.autoCreate(manageParkService.updateFacility(facility));
    }

    @PostMapping("/deleteFacility")
    @ResponseBody
    public CommonReturnType deleteFacility(@RequestBody String facilityName){
        return CommonReturnType.autoCreate(manageParkService.deleteFacility(facilityName));
    }

    @PostMapping(value="/checkTicket")
    @ResponseBody
    public CommonReturnType checkTicket(@RequestBody String ticketId){
        System.out.println(ticketId);
        ErrorEnum errorEnum = checkInService.checkTicket(ticketId);
        return CommonReturnType.autoCreate(errorEnum);
    }

    @PostMapping("/checkAppointment")
    @ResponseBody
    public CommonReturnType checkAppointment(@RequestBody String appointmentId){
        System.out.println(appointmentId);
        return CommonReturnType.autoCreate(checkInService.checkAppointments(appointmentId));
    }

}
