package com.java.rollercoaster.controller;


import com.java.rollercoaster.dao.AnnouncementMapper;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.response.CommonReturnType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AnnouncementControllerTest {
    @Autowired
    private AnnouncementController announcementController;
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Test
    public void testGetAnnouncements() {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        announcementMapper.insert(announcement);
        int id = announcement.getAnnouncementId();
        announcement.setAnnouncementId(id);
        CommonReturnType response = announcementController.getAnnouncements();
        assertEquals("success", response.getStatus());
        List<Announcement> announcementList = (List<Announcement>) response.getData();
        boolean flag = false;
        for (Announcement result : announcementList) {
            if (result.getText().equals(announcement.getText()) &&
                    result.getAnnouncementId() == id) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
        announcementMapper.deleteByPrimaryKey(id);
    }
}
