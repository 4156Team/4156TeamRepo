package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.service.MailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Override
    public  ErrorEnum sendAnnouncementMessage(String toEmail, String text) throws BusinessException, UnirestException {
        HttpResponse<JsonNode> request = Unirest.post(
                "https://api.mailgun.net/v3/sandbox1ad7f0d6956b4fcdb728091dbebe3d7b.mailgun.org/messages")
                .basicAuth("api", "33e62e196215815f54660b4d5e261e6f-f7910792-838afa06")
                .queryString("from", "Roller Coaster <mailgun@sandbox1ad7f0d6956b4fcdb728091dbebe3d7b.mailgun.org>")
                .queryString("to", toEmail)
                .queryString("subject", "New announcement from Roller coaster")
                .queryString("text", text)
                .asJson();
        return ErrorEnum.OK;

    }

    @Override
    public ErrorEnum sendTicketMessage(String toEmail, String text) throws UnirestException {
            HttpResponse<JsonNode> request = Unirest.post(
                    "https://api.mailgun.net/v3/sandbox1ad7f0d6956b4fcdb728091dbebe3d7b.mailgun.org/messages")
                    .basicAuth("api", "33e62e196215815f54660b4d5e261e6f-f7910792-838afa06")
                    .queryString("from", "Roller Coaster <mailgun@sandbox1ad7f0d6956b4fcdb728091dbebe3d7b.mailgun.org>")
                    .queryString("to", toEmail)
                    .queryString("subject", "Your ticket of Roller coaster!")
                    .queryString("text", text)
                    .asJson();
            return ErrorEnum.OK;


    }




//    public static void main(String[] args) throws UnirestException {
//        System.out.println(sendAnnouncementMessage("yl4225@columbia.edu", "test").getObject().get("message").equals("Queued. Thank you."));
//    }
}
