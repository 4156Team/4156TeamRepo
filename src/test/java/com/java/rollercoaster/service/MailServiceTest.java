package com.java.rollercoaster.service;

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
    public void sendAnnouncementTest() throws UnirestException {
       String message = (String) mailService.sendAnnouncementMessage("yl4225@columbia.edu", "Announcement test").getObject().get("message");
        assertEquals("Queued. Thank you.", message);

    }

    @Test
    public void sendTicketTest() throws UnirestException {
        String message = (String) mailService.sendTicketMessage("yl4225@columbia.edu", "Ticket test").getObject().get("message");
        assertEquals("Queued. Thank you.", message);

    }

}
