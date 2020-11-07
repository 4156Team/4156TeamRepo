package com.java.rollercoaster.pojo;

public enum FacilityStatus {
    repairing("repairing"),
    normal("normal");
    private String status;
    FacilityStatus(String status){
        this.status = status;
    }
}
