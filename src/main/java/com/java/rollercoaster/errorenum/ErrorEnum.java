package com.java.rollercoaster.errorenum;

public enum ErrorEnum implements CommonError {
    OK(100, ""),
    PARAMETER_VALIDATION_ERROR(10001, "Invalid parameter."),
    UNKNOWN_ERROR(10002, "unknown error"),
    USER_NOT_EXIST(20001, "User not exist"),
    USER_NOT_LOGIN(20003, "User Not Login"),
    USER_LOGIN_FAIL(20002, "User or password is incorrect"),

    INVALID_TICKET(201, "The ticket id is invalid in that day."),
    WRONG_TICKET_ID(202, "The ticket id is wrong."),
    USED_TICKET(203, "The ticket has been used."),
    WRONG_APPOINTMENT_ID(204, "The appointment id is wrong."),

    EMPTY_EVENT_NAME(221, "The event name is empty."),
    DUPLICATE_EVENT_NAME(222, "The event name is duplicate."),
    NO_SUCH_EVENT(223, "The event does not exist"),
    EMPTY_FACILITY_NAME(224, "The facility name is empty."),
    DUPLICATE_FACILITY_NAME(225, "The facility name is duplicate."),
    NO_SUCH_FACILITY(226, "The facility does not exist"),

    EMPTY_TICKET(241, "Try to operate empty ticket."),
    DUPLICATE_TICKET(242, "Ticket already existed."),
    DATE_PASSED(243, "The date chosen has already passed."),
    NO_SUCH_TICKET(244, "Ticket does not exist."),

    EMPTY_APPOINTMENT(251, "Try to operate empty appointment."),
    DUPLICATE_APPOINTMENT(252, "The appointment is duplicate."),
    EVENT_NO_POSITION(253, "This event has no remaining position."),
    NO_SUCH_APPOINTMENT(254, "The appointment does not exist.");

    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    ErrorEnum(int code, String message) {
        this.errorCode = code;
        this.errorMessage = message;
    }

    @Override
    public int getErrCode() {
        return this.errorCode;
    }

    @Override
    public String getErrMsg() {
        return this.errorMessage;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errorMessage = errMsg;
        return this;
    }
}
