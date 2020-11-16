package com.java.rollercoaster.pojo.enumeration;

public enum Status {
    used("used"),
    unused("unused");
    private String currentStatus;
    Status(String currentStatus) {
        this.currentStatus = currentStatus;
    }
}
