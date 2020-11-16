package com.java.rollercoaster.service.model;

import com.java.rollercoaster.service.model.enumeration.Status;

import java.util.Date;

public class TicketModel {
    private String ticketId;
    private Integer userId;
    private Status status;
    private Float price;
    private Date validDate;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public com.java.rollercoaster.service.model.enumeration.Status getStatus() {
        return status;
    }

    public void setStatus(com.java.rollercoaster.service.model.enumeration.Status status) {
        this.status = status;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getValidDate() {
        return new Date(validDate.getTime());
    }

    public void setValidDate(Date validDate) {
        this.validDate = new Date(validDate.getTime());
    }
}
