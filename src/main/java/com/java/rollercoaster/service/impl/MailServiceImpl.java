package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.service.MailService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Service
@PropertySource(value = {"classpath:apiKey.properties"})
public class MailServiceImpl implements MailService {

    @Override
    public  ErrorEnum sendAnnouncementMessage(String toEmail, String text)
            throws UnirestException {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.transport.protocol", "smtp");
        try {
            MailSSLSocketFactory msf = new MailSSLSocketFactory();
            msf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory",msf);
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2313368506@qq.com","mwgezkmnephhdjde");
                }
            });
            Message message = new MimeMessage(session);
            message.setSubject("New announcement from Roller coaster");
            message.setText(text);
            message.setFrom(new InternetAddress("2313368506@qq.com"));
            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message,new Address[]{new InternetAddress(toEmail)});
            transport.close();
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
            return ErrorEnum.SEND_MAIL_FAILED;
        }
        System.out.println("Ok");
        return ErrorEnum.OK;
    }


    @Override
    public ErrorEnum sendTicketMessage(String toEmail, String text) throws UnirestException {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.transport.protocol", "smtp");
        try {
            MailSSLSocketFactory msf = new MailSSLSocketFactory();
            msf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory",msf);
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("2313368506@qq.com","mwgezkmnephhdjde");
                }
            });
            Message message = new MimeMessage(session);
            message.setSubject("Your ticket of Roller coaster!");
            message.setText(text);
            message.setFrom(new InternetAddress("2313368506@qq.com"));
            Transport transport = session.getTransport();
            transport.connect();
            transport.sendMessage(message,new Address[]{new InternetAddress(toEmail)});
            transport.close();
        } catch (Exception e1) {
            System.out.println(e1.getMessage());
            return ErrorEnum.SEND_MAIL_FAILED;
        }
        System.out.println("Ok");
        return ErrorEnum.OK;
    }




}
