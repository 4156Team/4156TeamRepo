package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendAnnouncementSuccessfullyTest() throws UnirestException, GeneralSecurityException, MessagingException {
        assertEquals(ErrorEnum.OK, mailService.sendAnnouncementMessage("yl4225@columbia.edu", "Announcement test"));

    }
//    @Test
//    public void failToSendAnnouncementTest() throws UnirestException, GeneralSecurityException, MessagingException {
//        assertEquals(ErrorEnum.SEND_MAIL_FAILED, mailService.sendAnnouncementMessage("example@colg.edu", "Announcement test"));
//    }

    @Test
    public void sendTicketSuccessfullyTest() throws UnirestException, GeneralSecurityException, MessagingException {
        assertEquals(ErrorEnum.OK, mailService.sendTicketMessage("yl4225@columbia.edu", "Ticket test"));
    }

//    @Test
//    public void failToSendTicketTest() throws UnirestException, GeneralSecurityException, MessagingException {
//        assertEquals(ErrorEnum.SEND_MAIL_FAILED, mailService.sendTicketMessage("example@colg.edu", "Announcement test"));
//    }



}
