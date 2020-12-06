package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.service.MailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public  ErrorEnum sendAnnouncementMessage(String toEmail, String text)
            throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post(
                "https://api.mailgun.net/v3/sandbox1ad7f0d6956b4fcdb728091dbebe3d7b.mailgun.org/messages")
                .basicAuth("api", "key-61622357bb046ca01bbaf824e03e9b34")
                .queryString("from",
                        "Roller Coaster <mailgun@sandbox1ad7f0d6956b4fcdb728091dbebe3d7b"
                                + ".mailgun.org>")
                .queryString("to", toEmail)
                .queryString("subject", "New announcement from Roller coaster")
                .queryString("text", text)
                .asJson();
        if (request.getStatus() != 200 ) {
            return ErrorEnum.SEND_MAIL_FAILED;
        }
        return ErrorEnum.OK;

    }

    @Override
    public ErrorEnum sendTicketMessage(String toEmail, String text) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post(
                "https://api.mailgun.net/v3/sandbox1ad7f0d6956b4fcdb728091dbebe3d7b.mailgun.org/messages")
                .basicAuth("api", "key-61622357bb046ca01bbaf824e03e9b34")
                .queryString("from",
                        "Roller Coaster <mailgun@sandbox1ad7f0d6956b4fcdb728091dbebe3d7b"
                        + ".mailgun.org>")
                .queryString("to", toEmail)
                .queryString("subject", "Your ticket of Roller coaster!")
                .queryString("text", text)
                .asJson();

        if (request.getStatus() != 200 ) {
            return ErrorEnum.SEND_MAIL_FAILED;
        }

        return ErrorEnum.OK;

    }




}
