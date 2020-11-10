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
    public ErrorEnum addEvent(@RequestBody Event event){
        return manageParkService.addEvent(event);
    }

    @PostMapping("/updateEvent")
    @ResponseBody
    public ErrorEnum updateEvent(@RequestBody Event event){
        return manageParkService.updateEvent(event);
    }

    @PostMapping("/deleteEvent")
    @ResponseBody
    public ErrorEnum deleteEvent(@RequestBody String eventName){
        return manageParkService.deleteEvent(eventName);
    }

    @PostMapping("/addFacility")
    @ResponseBody
    public ErrorEnum addFacility(@RequestBody Facility facility){
        return manageParkService.addFacility(facility);
    }

    @PostMapping("/updateFacility")
    @ResponseBody
    public ErrorEnum updateFacility(@RequestBody Facility facility){
        return manageParkService.updateFacility(facility);
    }

    @PostMapping("/deleteFacility")
    @ResponseBody
    public ErrorEnum deleteFacility(@RequestBody String facilityName){
        return manageParkService.deleteFacility(facilityName);
    }

    @PostMapping(value="/checkTicket")
    @ResponseBody
    public CommonReturnType checkTicket(@RequestBody String ticketId){
        System.out.println(ticketId);
        ErrorEnum errorEnum = checkInService.checkTicket(ticketId);
        if(ErrorEnum.OK == errorEnum){
            return CommonReturnType.create(errorEnum);
        } else{
            return CommonReturnType.create(errorEnum, "fail");
        }
    }

    @PostMapping("/checkAppointment")
    @ResponseBody
    public ErrorEnum checkAppointment(@RequestBody String appointmentId){
        return checkInService.checkAppointments(appointmentId);
    }

    @RequestMapping("")
    public String manageView(){
        return "/managerView";
    }

}
