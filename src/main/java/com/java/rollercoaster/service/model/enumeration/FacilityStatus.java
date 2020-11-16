package com.java.rollercoaster.service.model.enumeration;

public enum FacilityStatus {
    repairing("repairing"),
    normal("normal");
    private String status;
    FacilityStatus(String status) {
        this.status = status;
    }
}
