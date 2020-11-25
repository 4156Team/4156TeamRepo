package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.dao.AnnouncementMapper;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;
import com.java.rollercoaster.pojo.AnnouncementExample;
import com.java.rollercoaster.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public ErrorEnum pushAnnouncement(Announcement announcement) {
        if ("".equals(announcement.getText()) || null == announcement.getText()) {
            return ErrorEnum.EMPTY_ANNOUNCEMENT_ATTRIBUTE;
        }
        announcementMapper.insert(announcement);
        return ErrorEnum.OK;
    }

    @Override
    public List<Announcement> getAnnouncements() {
        return announcementMapper.selectByExample(new AnnouncementExample());
    }
}
