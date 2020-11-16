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

    /**
     * Get event start time with null check.
     *
     */
    public Date getStartTime() {
        if (this.startTime == null) {
            return null;
        }
        return (Date) startTime.clone();
    }

    /**
     * Set event start time with null check.
     *
     */
    public void setStartTime(Date startTime) {
        if (startTime == null) {
            this.startTime = null;
        } else {
            Date newStartTime = (Date) startTime.clone();
            this.startTime = newStartTime;
        }

    }

    /**
     * Get event end time with null check.
     *
     */
    public Date getEndTime() {
        if (this.endTime == null) {
            return null;
        }
        return (Date) endTime.clone();
    }

    /**
     * Set event end time with null check.
     *
     */
    public void setEndTime(final Date endTime) {
        if (endTime == null) {
            this.endTime = null;
        } else {
            Date newEndTime = (Date) endTime.clone();
            this.endTime = newEndTime;
        }

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

    public void setEventRemainPositions(final Integer newEventRemainPositions) {
        this.eventRemainPositions = newEventRemainPositions;
    }
}
