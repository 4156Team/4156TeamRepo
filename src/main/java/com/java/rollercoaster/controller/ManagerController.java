package com.java.rollercoaster.controller;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;
import com.java.rollercoaster.pojo.Facility;
import com.java.rollercoaster.pojo.QuickPass;
import com.java.rollercoaster.pojo.Ticket;
import com.java.rollercoaster.pojo.Type;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.AnnouncementService;
import com.java.rollercoaster.service.CheckInService;
import com.java.rollercoaster.service.ManageParkService;
import com.java.rollercoaster.service.StatisticCollectionService;
import com.java.rollercoaster.service.TicketPriceService;
import com.java.rollercoaster.service.model.MyCalendar;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;

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

    @Autowired
    private StatisticCollectionService statisticCollectionService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private TicketPriceService ticketPriceService;

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
     * @param ticket ticket
     * @return CommonReturnType
     */
    @PostMapping("/checkTicket")
    @ResponseBody
    public CommonReturnType checkTicket(@RequestBody
                                        Ticket ticket) {
        ErrorEnum errorEnum = checkInService.checkTicket(ticket.getTicketId());
        return CommonReturnType.autoCreate(errorEnum);
    }

    /**
     * Check appointment.
     *
     * @param appointment appointment
     * @return CommonReturnType
     */
    @PostMapping("/checkAppointment")
    @ResponseBody
    public CommonReturnType checkAppointment(@RequestBody
                                             Appointment appointment) {
        return CommonReturnType.autoCreate(
                checkInService.checkAppointments(appointment.getAppointmentId()));
    }

    /**
     * Check QuickPass.
     * @param quickPass quickPass
     * @return CommonReturnType
     */
    @PostMapping("/checkQuickPass")
    @ResponseBody
    public CommonReturnType checkQuickPass(@RequestBody
                                           QuickPass quickPass) {
        return CommonReturnType.autoCreate(
                checkInService.checkQuickPass(quickPass.getQuickpassId()));
    }

    /**
     * Get the the statistic of how many people visited
     * in the park for the given date.
     * @param myCalendar myCalendar
     * @return CommonReturnType
     */
    @PostMapping("/peopleInThatDay")
    @ResponseBody
    public CommonReturnType peopleInThatDay(@RequestBody MyCalendar myCalendar) {
        try {
            return CommonReturnType.create(
                    statisticCollectionService.peopleInThatDay(myCalendar),"success");
        } catch (BusinessException businessException) {
            return CommonReturnType.create(businessException.getCommonError(), "fail");
        }
    }

    /**
     * Get the the statistic of how many people visited
     * in the park for the given month.
     * @param myCalendar myCalendar
     * @return CommonReturnType
     */
    @PostMapping("/peopleInThatMonth")
    @ResponseBody
    public CommonReturnType peopleInThatMonth(@RequestBody MyCalendar myCalendar) {
        try {
            return CommonReturnType.create(
                    statisticCollectionService.peopleInThatMonth(myCalendar),"success");
        } catch (BusinessException businessException) {
            return CommonReturnType.create(businessException.getCommonError(), "fail");
        }
    }

    /**
     * Get the the statistic of how many people visited
     * in the park for the given year.
     * @param myCalendar myCalendar
     * @return CommonReturnType
     */
    @PostMapping("/peopleInThatYear")
    @ResponseBody
    public CommonReturnType peopleInThatYear(@RequestBody MyCalendar myCalendar) {
        try {
            return CommonReturnType.create(
                    statisticCollectionService.peopleInThatYear(myCalendar),"success");
        } catch (BusinessException businessException) {
            return CommonReturnType.create(businessException.getCommonError(), "fail");
        }
    }

    @PostMapping("/whichDaysVisited")
    @ResponseBody
    public CommonReturnType whichDaysVisited(@RequestParam("userId") int userId) {
        return CommonReturnType.create(
                statisticCollectionService.whichDaysVisited(userId),"success");
    }

    @RequestMapping("top5Facility")
    @ResponseBody
    public CommonReturnType top5Facility() throws BusinessException {
        return CommonReturnType.create(
                statisticCollectionService.top5Facility(), "success");
    }


    @PostMapping("/changeTicketPrice")
    @ResponseBody
    public CommonReturnType changeTicketPrice(@RequestBody Type type) {
        return CommonReturnType.autoCreate(ticketPriceService.changeTicketPrice(type));
    }

    @PostMapping("/pushAnnouncement")
    @ResponseBody
    public CommonReturnType pushAnnouncement(@RequestBody Announcement announcement)
            throws UnirestException, BusinessException,
            GeneralSecurityException, MessagingException {
        return CommonReturnType.autoCreate(announcementService.pushAnnouncement(announcement));
    }

    @PostMapping("/deleteAnnouncement")
    @ResponseBody
    public CommonReturnType deleteAnnouncement(@RequestBody Announcement announcement) {
        return CommonReturnType.autoCreate(announcementService
                .deleteAnnouncement(announcement.getAnnouncementId()));
    }
}
