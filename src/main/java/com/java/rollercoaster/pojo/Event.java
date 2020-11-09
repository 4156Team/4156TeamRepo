package com.java.rollercoaster.pojo;

import java.util.Date;

public class Event {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.event_name
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    private String eventName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.event_introduction
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    private String eventIntroduction;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.start_time
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.end_time
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.event_location
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    private String eventLocation;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column event.event_remain_positions
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    private Integer eventRemainPositions;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.event_name
     *
     * @return the value of event.event_name
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.event_name
     *
     * @param eventName the value for event.event_name
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.event_introduction
     *
     * @return the value of event.event_introduction
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public String getEventIntroduction() {
        return eventIntroduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.event_introduction
     *
     * @param eventIntroduction the value for event.event_introduction
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public void setEventIntroduction(String eventIntroduction) {
        this.eventIntroduction = eventIntroduction == null ? null : eventIntroduction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.start_time
     *
     * @return the value of event.start_time
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.start_time
     *
     * @param startTime the value for event.start_time
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.end_time
     *
     * @return the value of event.end_time
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.end_time
     *
     * @param endTime the value for event.end_time
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.event_location
     *
     * @return the value of event.event_location
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.event_location
     *
     * @param eventLocation the value for event.event_location
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation == null ? null : eventLocation.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column event.event_remain_positions
     *
     * @return the value of event.event_remain_positions
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public Integer getEventRemainPositions() {
        return eventRemainPositions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column event.event_remain_positions
     *
     * @param eventRemainPositions the value for event.event_remain_positions
     *
     * @mbggenerated Mon Nov 09 11:38:28 CST 2020
     */
    public void setEventRemainPositions(Integer eventRemainPositions) {
        this.eventRemainPositions = eventRemainPositions;
    }
}