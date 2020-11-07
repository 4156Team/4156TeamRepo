package com.java.rollercoaster.errorEnum;

public enum ErrorEnum {
    OK(100, ""),
    INVALID_TICKET(201,"The ticket id is invalid in that day."),
    WRONG_TICKET_ID(202, "The ticket id is wrong.");
    private int errorCode;
    private String errorMessage;
    ErrorEnum(int code, String message){
        this.errorCode = code;
        this.errorMessage = message;
    }
}
