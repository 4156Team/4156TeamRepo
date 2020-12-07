package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.mashape.unirest.http.exceptions.UnirestException;


import java.security.GeneralSecurityException;

import javax.mail.MessagingException;

public interface MailService {
    ErrorEnum sendAnnouncementMessage(String toEmail, String text)
            throws UnirestException, MessagingException, GeneralSecurityException;

    ErrorEnum sendTicketMessage(String toEmail, String text)
            throws UnirestException, GeneralSecurityException, MessagingException;

}
