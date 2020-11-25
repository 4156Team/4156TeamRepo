package com.java.rollercoaster.service;

import java.io.File;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface MailService {
    JsonNode sendAnnouncementMessage(String toEmail, String text) throws UnirestException;

    JsonNode sendTicketMessage(String toEmail, String text) throws UnirestException;

}
