package com.java.rollercoaster.errorenum;

public enum ErrorEnum implements CommonError {
    OK(100, ""),
    PARAMETER_VALIDATION_ERROR(10001, "Invalid parameter."),
    UNKNOWN_ERROR(10002, "unknown error"),
    USER_NOT_EXIST(20001, "User not exist"),
    USER_NOT_LOGIN(20003, "User Not Login"),
    USER_LOGIN_FAIL(20002, "User or password is incorrect"),
    NO_AUTHORIZATION(20004, "No access authorization"),
    SEND_MAIL_FAILED(20005, "Mail sending failed"),
    COMMENT_POST_FAILED(20006, "Comment post failed"),
    UNAUTHORIZED_DELETION(20007, "You cannot delete other's comments"),
    INVALID_COMMENT(20008, "Invalid Comment"),

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
    NO_SUCH_APPOINTMENT(254, "The appointment does not exist."),
    NOT_SAME_VISITOR(255, "Try to modify other visitor's information."),
    WRONG_DATE(256, "Cannot get the weather of that date."),

    EMPTY_DATE_ATTRIBUTE(261, "The day or month or year parameter is missing."),
    TIME_OVER_CURRENT_DAY(262, "The date is ahead of current date."),
    EMPTY_TYPE_ATTRIBUTE(271, "The ticket type or ticket price is missing."),
    EMPTY_ANNOUNCEMENT_ATTRIBUTE(281, "The text or date is missing."),
    EMPTY_ANNOUNCEMENT_ID(282, "The announcement id is empty."),
    WRONG_ANNOUNCEMENT_ID(283, "The announcement id is wrong."),

    INVALID_AMOUNT(300, "Invalid operation amount"),
    BALANCE_NOT_ENOUGH(301, "Balance not enough for operation");


    private int errorCode;
    private String errorMessage;

    public int getErrorCode() {
        return this.errorCode;
    }

    private void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    private void setErrorMessage(String errorMessage) {
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

}
