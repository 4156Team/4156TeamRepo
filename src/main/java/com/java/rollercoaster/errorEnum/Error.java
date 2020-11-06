package com.java.rollercoaster.errorEnum;

public enum Error {
    INVALID_TICKET(201,"The ticket id is invalid.");
    private int errorCode;
    private String errorMessage;
    Error(int code, String message){
        this.errorCode = code;
        this.errorMessage = message;
    }
}
