package com.java.rollercoaster.service;

import com.java.rollercoaster.dao.AnnouncementMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.AnnouncementExample;
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
public class AnnouncementServiceTest {
    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private AnnouncementService announcementService;

    @Test
    public void testPushAnnouncement() {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        assertEquals(ErrorEnum.OK, announcementService.pushAnnouncement(announcement));
        AnnouncementExample announcementExample = new AnnouncementExample();
        announcementExample.createCriteria().andTextEqualTo("test Announcement");
        List<Announcement> announcements = announcementMapper.selectByExample(announcementExample);
        assertEquals(1, announcements.size());
        announcementMapper.deleteByExample(announcementExample);
    }

    @Test
    public void testPushAnnouncementFail() {
        Announcement announcement = new Announcement();
        announcement.setText("");
        announcement.setDate(new Date());
        assertEquals(ErrorEnum.EMPTY_ANNOUNCEMENT_ATTRIBUTE, announcementService.pushAnnouncement(announcement));
    }

    @Test
    public void testGetAnnouncements() {
        Announcement announcement = new Announcement();
        announcement.setText("test Announcement");
        announcement.setDate(new Date());
        announcementMapper.insert(announcement);
        int id = announcement.getAnnouncementId();
        announcement.setAnnouncementId(id);
        List<Announcement> announcementList = announcementService.getAnnouncements();
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
