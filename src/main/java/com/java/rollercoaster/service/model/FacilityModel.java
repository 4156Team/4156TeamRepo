package com.java.rollercoaster.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java.rollercoaster.pojo.enumeration.FacilityStatus;

import java.util.Date;

public class FacilityModel {

    private String facilityName;
    private FacilityStatus FacilityStatus;
    private String facilityIntroduction;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date facilityOpenTime;
    @JsonFormat(pattern = "HH:mm:ss")
    private Date facilityCloseTime;
    private Integer queueStatus;

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public com.java.rollercoaster.pojo.enumeration.FacilityStatus getFacilityStatus() {
        return FacilityStatus;
    }

    public void setFacilityStatus(com.java.rollercoaster.pojo.enumeration.FacilityStatus facilityStatus) {
        FacilityStatus = facilityStatus;
    }

    public String getFacilityIntroduction() {
        return facilityIntroduction;
    }

    public void setFacilityIntroduction(String facilityIntroduction) {
        this.facilityIntroduction = facilityIntroduction;
    }

    public Date getFacilityOpenTime() {
        return facilityOpenTime;
    }

    public void setFacilityOpenTime(Date facilityOpenTime) {
        this.facilityOpenTime = facilityOpenTime;
    }

    public Date getFacilityCloseTime() {
        return facilityCloseTime;
    }

    public void setFacilityCloseTime(Date facilityCloseTime) {
        this.facilityCloseTime = facilityCloseTime;
    }

    public Integer getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(Integer queueStatus) {
        this.queueStatus = queueStatus;
    }
}
