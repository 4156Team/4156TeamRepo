package com.java.rollercoaster.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.rollercoaster.pojo.Appointment;
import com.java.rollercoaster.pojo.Event;

import java.util.Date;

public class TimedAppointmentModel extends Appointment {
    private String appointmentId;
    private String eventName;
    private Integer userId;
    private Date validDate;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8:00")
    private Date startTime;

    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8:00")
    private Date endTime;

    private String eventLocation;

    public TimedAppointmentModel() {

    }

    public TimedAppointmentModel(Appointment appointment, Event event) {
        this.appointmentId = appointment.getAppointmentId();
        this.userId = appointment.getUserId();
        this.eventName = appointment.getEventName();
        this.validDate = appointment.getValidDate();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.eventLocation = event.getEventLocation();
    }

    @Override
    public String getAppointmentId() {
        return appointmentId;
    }

    @Override
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String getEventName() {
        return eventName;
    }

    @Override
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public Date getValidDate() {
        return validDate;
    }

    @Override
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    @Override
    public Integer getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
}
