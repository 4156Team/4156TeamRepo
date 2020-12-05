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
            throws BusinessException, UnirestException {
//        Unirest.post(
//                "https://api.mailgun.net/v3/sandbox577c98fd40d242c3bf95fbedf216dd8a.mailgun.org/messages")
//                .basicAuth("api", "2362f0e8ad721fcfe14b2a6507ef27a6-95f6ca46-cee0c017")
//                .queryString("from",
//                        "Roller Coaster <mailgun@sandbox577c98fd40d242c3bf95fbedf216dd8a"
//                                + ".mailgun.org>")
//                .queryString("to", toEmail)
//                .queryString("subject", "New announcement from Roller coaster")
//                .queryString("text", text)
//                .asJson();
        return ErrorEnum.OK;

    }

    @Override
    public ErrorEnum sendTicketMessage(String toEmail, String text) throws UnirestException {
//        HttpResponse<JsonNode> request = Unirest.post(
//                "https://api.mailgun.net/v3/sandbox577c98fd40d242c3bf95fbedf216dd8a.mailgun.org/messages")
//                .basicAuth("api", "2362f0e8ad721fcfe14b2a6507ef27a6-95f6ca46-cee0c017")
//                .queryString("from",
//                        "Roller Coaster <mailgun@sandbox577c98fd40d242c3bf95fbedf216dd8a"
//                        + ".mailgun.org>")
//                .queryString("to", toEmail)
//                .queryString("subject", "Your ticket of Roller coaster!")
//                .queryString("text", text)
//                .asJson();

        return ErrorEnum.OK;

    }

}
