package com.java.rollercoaster.service.model.enumeration;

public enum TicketType {
    adult("adult"),
    student("student"),
    child("child"),
    quickPass("quickPass");
    private String type;
    TicketType(String type) {
        this.type = type;
    }
}
