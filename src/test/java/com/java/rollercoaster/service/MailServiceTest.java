package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendAnnouncementTest() throws BusinessException, UnirestException {
//       String message = (String) mailService.sendAnnouncementMessage("yl4225@columbia.edu", "Announcement test").getObject().get("message");
//        assertEquals("Queued. Thank you.", message);
        assertEquals(ErrorEnum.OK, mailService.sendAnnouncementMessage("yl4225@columbia.edu", "Announcement test"));

    }

    @Test
    public void sendTicketTest() throws UnirestException, BusinessException {
        assertEquals(ErrorEnum.OK, mailService.sendAnnouncementMessage("yl4225@columbia.edu", "Ticket test"));

    }



}
