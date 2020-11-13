package com.java.rollercoaster.service.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EventModel {

    private String eventName;
    private String eventIntroduction;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date startTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date endTime;
    private String eventLocation;
    private Integer eventRemainPositions;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventIntroduction() {
        return eventIntroduction;
    }

    public void setEventIntroduction(String eventIntroduction) {
        this.eventIntroduction = eventIntroduction;
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

    public Integer getEventRemainPositions() {
        return eventRemainPositions;
    }

    public void setEventRemainPositions(Integer eventRemainPositions) {
        this.eventRemainPositions = eventRemainPositions;
    }
}
