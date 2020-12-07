package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.service.MailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = {"classpath:apiKey.properties"})
public class MailServiceImpl implements MailService {
    @Value("${mail.url}")
    private String url;
    @Value("${mail.apiKey}")
    private String apiKey;
    @Value("${mail.address}")
    private String mailFrom;

    @Override
    public  ErrorEnum sendAnnouncementMessage(String toEmail, String text)
            throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post(url)
                .basicAuth("api", apiKey)
                .queryString("from", mailFrom)
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
        HttpResponse<JsonNode> request = Unirest.post(url)
                .basicAuth("api", apiKey)
                .queryString("from", mailFrom)
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
