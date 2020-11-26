package com.java.rollercoaster.service;

import java.io.File;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface MailService {
    ErrorEnum sendAnnouncementMessage(String toEmail, String text) throws UnirestException, BusinessException;

    ErrorEnum sendTicketMessage(String toEmail, String text) throws UnirestException, BusinessException;

}
