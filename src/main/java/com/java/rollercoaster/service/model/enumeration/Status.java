package com.java.rollercoaster.service.model.enumeration;

public enum Status {
    used("used"),
    unused("unused");
    private String currentStatus;
    Status(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
