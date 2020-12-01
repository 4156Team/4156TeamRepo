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
        if (this.facilityOpenTime == null) {
            return null;
        }
        return (Date) facilityOpenTime.clone();
    }

    /**
     * Set facility open time with null check.
     *
     */
    public void setFacilityOpenTime(Date facilityOpenTime) {
        if (facilityOpenTime == null) {
            this.facilityOpenTime = null;
        } else {
            this.facilityOpenTime = (Date) facilityOpenTime.clone();
        }

    }

    /**
     * Get facility close time with null check.
     *
     */
    public Date getFacilityCloseTime() {
        if (this.facilityCloseTime == null) {
            return null;
        }
        return (Date) facilityCloseTime.clone();
    }

    /**
     * Set facility close time with null check.
     *
     */
    public void setFacilityCloseTime(Date facilityCloseTime) {
        if (facilityCloseTime == null) {
            this.facilityCloseTime = null;
        } else {
            this.facilityCloseTime = (Date) facilityCloseTime.clone();
        }
    }

    public Integer getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(Integer queueStatus) {
        this.queueStatus = queueStatus;
    }
}
