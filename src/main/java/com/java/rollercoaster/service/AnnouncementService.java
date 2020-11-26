package com.java.rollercoaster.service;

import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.pojo.Announcement;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> getAnnouncements();

    ErrorEnum pushAnnouncement(Announcement announcement);
}
