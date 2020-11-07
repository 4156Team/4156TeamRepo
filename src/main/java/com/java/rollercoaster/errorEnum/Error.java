package com.java.rollercoaster.errorEnum;

public enum Error {
    OK(100, ""),
    USER_NOT_EXIST(10000,"This user does not exist"),
    INVALID_TICKET(201,"The ticket id is invalid in that day."),
    WRONG_TICKET_ID(202, "The ticket id is wrong.");
    public int errorCode;
    public String errorMessage;
    Error(int code, String message){
        this.errorCode = code;
        this.errorMessage = message;
    }
}
