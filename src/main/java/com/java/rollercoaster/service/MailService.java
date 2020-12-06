package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface MailService {
    ErrorEnum sendAnnouncementMessage(String toEmail, String text)
            throws UnirestException, BusinessException;

//    ErrorEnum sendTicketMessage(String toEmail, String text)
//            throws UnirestException, BusinessException;

}
