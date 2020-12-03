package com.java.rollercoaster.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.rollercoaster.service.model.enumeration.FacilityStatus;

import java.util.Date;

public class FacilityModel {

    private String facilityName;
    private FacilityStatus facilityStatus;
    private String facilityIntroduction;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date facilityOpenTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date facilityCloseTime;
    private Integer queueStatus;
    private String facilityImage;

    public String getFacilityImage() {
        return facilityImage;
    }

    public void setFacilityImage(String facilityImage) {
        this.facilityImage = facilityImage;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getRatingPeople() {
        return ratingPeople;
    }

    public void setRatingPeople(Integer ratingPeople) {
        this.ratingPeople = ratingPeople;
    }

    private Float rating;
    private Integer ratingPeople;

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public com.java.rollercoaster.service.model.enumeration.FacilityStatus getFacilityStatus() {
        return facilityStatus;
    }

    public void setFacilityStatus(FacilityStatus facilityStatus) {
        this.facilityStatus = facilityStatus;
    }

    public String getFacilityIntroduction() {
        return facilityIntroduction;
    }

    public void setFacilityIntroduction(String facilityIntroduction) {
        this.facilityIntroduction = facilityIntroduction;
    }

    /**
     * Get facility open time with null check.
     *
     */
    public Date getFacilityOpenTime() {
        return new Date(facilityOpenTime.getTime());
    }

    /**
     * Set facility open time with null check.
     *
     */
    public void setFacilityOpenTime(Date facilityOpenTime) {
        this.facilityOpenTime = new Date(facilityOpenTime.getTime());

    }

    /**
     * Get facility close time with null check.
     *
     */
    public Date getFacilityCloseTime() {
        return new Date(facilityCloseTime.getTime());
    }

    /**
     * Set facility close time with null check.
     *
     */
    public void setFacilityCloseTime(Date facilityCloseTime) {
        this.facilityCloseTime = new Date(facilityCloseTime.getTime());
    }

    public Integer getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(Integer queueStatus) {
        this.queueStatus = queueStatus;
    }
}
