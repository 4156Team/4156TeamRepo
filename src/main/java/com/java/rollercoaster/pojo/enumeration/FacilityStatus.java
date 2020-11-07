package com.java.rollercoaster.pojo.enumeration;

public enum FacilityStatus {
    repairing("repairing"),
    normal("normal");
    private String status;
    FacilityStatus(String status){
        this.status = status;
    }
}
