package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.CheckInService;
import com.java.rollercoaster.service.ManageParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    /**
     * Service for managePark.
     */
    @Autowired
    private ManageParkService manageParkService;
    /**
     * Service for checkIn.
     */
    @Autowired
    private CheckInService checkInService;

    /**
     * Add event.
     *
     * @param event event
     * @return CommonReturnType
     */
    @PostMapping("/addEvent")
    @ResponseBody
    public CommonReturnType addEvent(@RequestBody final Event event) {
        System.out.println(event.toString());
        return CommonReturnType.autoCreate(manageParkService.addEvent(event));
    }

    /**
     * Update event.
     *
     * @param event event
     * @return CommonReturnType
     */
    @PostMapping("/updateEvent")
    @ResponseBody
    public CommonReturnType updateEvent(@RequestBody final Event event) {
        System.out.println(event.toString());
        return CommonReturnType.autoCreate(
                manageParkService.updateEvent(event));
    }

    /**
     * Delete event.
     *
     * @param event event
     * @return CommonReturnType
     */
    @PostMapping("/deleteEvent")
    @ResponseBody
    public CommonReturnType deleteEvent(@RequestBody Event event) {
        return CommonReturnType.autoCreate(
                manageParkService.deleteEvent(event.getEventName()));
    }

    /**
     * Add facility.
     *
     * @param facility facility
     * @return CommonReturnType
     */
    @PostMapping("/addFacility")
    @ResponseBody
    public CommonReturnType addFacility(@RequestBody final Facility facility) {
        return CommonReturnType.autoCreate(
                manageParkService.addFacility(facility));
    }

    /**
     * Update facility.
     *
     * @param facility facility
     * @return CommonReturnType
     */
    @PostMapping("/updateFacility")
    @ResponseBody
    public CommonReturnType updateFacility(@RequestBody final Facility facility) {
        System.out.println(facility.toString());
        return CommonReturnType.autoCreate(
                manageParkService.updateFacility(facility));
    }

    /**
     * Delete facility.
     *
     * @param facility facility
     * @return CommonReturnType
     */
    @PostMapping("/deleteFacility")
    @ResponseBody
    public CommonReturnType deleteFacility(@RequestBody
                                               Facility facility) {
        return CommonReturnType.autoCreate(
                manageParkService.deleteFacility(facility.getFacilityName()));
    }

    /**
     * Check ticket.
     *
     * @param ticketId ticketId
     * @return CommonReturnType
     */
    @PostMapping("/checkTicket")
    @ResponseBody
    public CommonReturnType checkTicket(@RequestBody
                                            final String ticketId) {
        ErrorEnum errorEnum = checkInService.checkTicket(ticketId);
        return CommonReturnType.autoCreate(errorEnum);
    }

    /**
     * Check appointment.
     *
     * @param appointmentId appointmentId
     * @return CommonReturnType
     */
    @PostMapping("/checkAppointment")
    @ResponseBody
    public CommonReturnType checkAppointment(@RequestBody
                                                 final String appointmentId) {
        System.out.println(appointmentId);
        return CommonReturnType.autoCreate(
                checkInService.checkAppointments(appointmentId));
    }

}
