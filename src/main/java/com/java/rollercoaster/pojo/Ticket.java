package com.java.rollercoaster.pojo;

import com.java.rollercoaster.service.model.enumeration.Status;
import com.java.rollercoaster.service.model.enumeration.TicketType;
import java.util.Date;

public class Ticket {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.ticket_ID
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    private String ticketId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.user_id
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.status
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    private Status Status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.price
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    private Float price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.valid_date
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    private Date validDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ticket.ticket_type
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    private TicketType TicketType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.ticket_ID
     *
     * @return the value of ticket.ticket_ID
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.ticket_ID
     *
     * @param ticketId the value for ticket.ticket_ID
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId == null ? null : ticketId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.user_id
     *
     * @return the value of ticket.user_id
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.user_id
     *
     * @param userId the value for ticket.user_id
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.status
     *
     * @return the value of ticket.status
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public Status getStatus() {
        return Status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.status
     *
     * @param Status the value for ticket.status
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public void setStatus(Status Status) {
        this.Status = Status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.price
     *
     * @return the value of ticket.price
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.price
     *
     * @param price the value for ticket.price
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.valid_date
     *
     * @return the value of ticket.valid_date
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public Date getValidDate() {
        return validDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.valid_date
     *
     * @param validDate the value for ticket.valid_date
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ticket.ticket_type
     *
     * @return the value of ticket.ticket_type
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public TicketType getTicketType() {
        return TicketType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ticket.ticket_type
     *
     * @param TicketType the value for ticket.ticket_type
     *
     * @mbggenerated Sun Nov 22 15:29:32 CST 2020
     */
    public void setTicketType(TicketType TicketType) {
        this.TicketType = TicketType;
    }
}